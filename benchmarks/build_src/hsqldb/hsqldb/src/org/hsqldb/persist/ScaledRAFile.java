/* Copyright (c) 2001-2005, The HSQL Development Group
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.hsqldb.persist;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

import org.hsqldb.Trace;
import org.hsqldb.lib.HsqlByteArrayInputStream;
import org.hsqldb.lib.Storage;

// fredt@users 20030111 - patch 1.7.2 by bohgammer@users - pad file before seek() beyond end
// some incompatible JVM implementations do not allow seek beyond the existing end of file

/**
 * This class is a wapper for a random access file such as that used for
 * CACHED table storage.
 *
 * @author fredt@users
 * @version  1.8.0
 * @since  1.7.2
 */
class ScaledRAFile implements Storage {

    static final int         DATA_FILE_RAF  = 0;
    static final int         DATA_FILE_NIO  = 1;
    static final int         DATA_FILE_JAR  = 2;
    static final long        MAX_NIO_LENGTH = (1L << 28);
    final RandomAccessFile   file;
    private final boolean    readOnly;
    final String             fileName;
    boolean                  isNio;
    boolean                  bufferDirty = true;
    byte[]                   buffer      = new byte[4096];
    HsqlByteArrayInputStream ba = new HsqlByteArrayInputStream(buffer);
    long                     bufferOffset;

    //
    long       seekPosition;
    long       realPosition;
    static int cacheHit;

    /**
     * seekPosition is the position in seek() calls or after reading or writing
     * realPosition is the file position
     */
    static Storage newScaledRAFile(String name, boolean readonly, int type,
                                   String classname,
                                   String key)
                                   throws FileNotFoundException, IOException {

        if (classname != null) {
            try {
                Class       zclass      = Class.forName(classname);
                Constructor constructor = zclass.getConstructor(new Class[] {
                    String.class, Boolean.class, Object.class
                });

                return (Storage) constructor.newInstance(new Object[] {
                    name, new Boolean(readonly), key
                });
            } catch (ClassNotFoundException e) {
                throw new IOException();
            } catch (NoSuchMethodException e) {
                throw new IOException();
            } catch (InstantiationException e) {
                throw new IOException();
            } catch (IllegalAccessException e) {
                throw new IOException();
            } catch (java.lang.reflect.InvocationTargetException e) {
                throw new IOException();
            }
        }

        if (type == DATA_FILE_JAR) {
            return new ScaledRAFileInJar(name);
        } else if (type == DATA_FILE_RAF) {
            return new ScaledRAFile(name, readonly);
        } else {
            RandomAccessFile file = new RandomAccessFile(name, readonly ? "r"
                                                                        : "rw");

            if (file.length() > MAX_NIO_LENGTH) {
                return new ScaledRAFile(name, file, readonly);
            } else {
                file.close();
            }

            try {
                Class.forName("java.nio.MappedByteBuffer");

                Class c = Class.forName("org.hsqldb.persist.NIOScaledRAFile");
                Constructor constructor = c.getConstructor(new Class[] {
                    String.class, boolean.class
                });

                return (ScaledRAFile) constructor.newInstance(new Object[] {
                    name, new Boolean(readonly)
                });
            } catch (Exception e) {
                return new ScaledRAFile(name, readonly);
            }
        }
    }

    ScaledRAFile(String name, RandomAccessFile file,
                 boolean readonly) throws FileNotFoundException, IOException {

        this.readOnly = readonly;
        fileName      = name;
        this.file     = file;
    }

    ScaledRAFile(String name,
                 boolean readonly) throws FileNotFoundException, IOException {

        file          = new RandomAccessFile(name, readonly ? "r"
                                                            : "rw");
        this.readOnly = readonly;
        fileName      = name;
    }

    public long length() throws IOException {
        return file.length();
    }

    /**
     * Some JVM's do not allow seek beyond end of file, so zeros are written
     * first in that case. Reported by bohgammer@users in Open Disucssion
     * Forum.
     */
    public void seek(long position) throws IOException {

        if (file.length() < position) {
            file.seek(file.length());

            for (long ix = file.length(); ix < position; ix++) {
                file.write(0);
            }
        }

        seekPosition = position;
    }

    public long getFilePointer() throws IOException {
        return seekPosition;
    }

    private void readIntoBuffer() throws IOException {

        long filePos = seekPosition;

        bufferDirty = false;

        long subOffset  = filePos % buffer.length;
        long fileLength = file.length();
        long readLength = fileLength - (filePos - subOffset);

        if (readLength <= 0) {
            throw new IOException("read beyond end of file");
        }

        if (readLength > buffer.length) {
            readLength = buffer.length;
        }

        file.seek(filePos - subOffset);
        file.readFully(buffer, 0, (int) readLength);

        bufferOffset = filePos - subOffset;
        realPosition = bufferOffset + readLength;
    }

    public int read() throws IOException {

        long fileLength = file.length();

        if (seekPosition >= fileLength) {
            return -1;
        }

        if (bufferDirty || seekPosition < bufferOffset
                || seekPosition >= bufferOffset + buffer.length) {
            readIntoBuffer();
        } else {
            cacheHit++;
        }

        ba.reset();
        ba.skip(seekPosition - bufferOffset);

        int val = ba.read();

        seekPosition++;

        return val;
    }

    public long readLong() throws IOException {

        file.seek(seekPosition);

        realPosition = seekPosition;

        long value = file.readLong();

        realPosition += 8;
        seekPosition = realPosition;

        return value;
    }

    public int readInt() throws IOException {

        if (bufferDirty || seekPosition < bufferOffset
                || seekPosition >= bufferOffset + buffer.length) {
            readIntoBuffer();
        } else {
            cacheHit++;
        }

        ba.reset();
        ba.skip(seekPosition - bufferOffset);

        int val = ba.readInt();

        seekPosition += 4;

        return val;
    }

    public void read(byte[] b, int offset, int length) throws IOException {

        if (bufferDirty || seekPosition < bufferOffset
                || seekPosition >= bufferOffset + buffer.length) {
            readIntoBuffer();
        } else {
            cacheHit++;
        }

        ba.reset();
        ba.skip(seekPosition - bufferOffset);

        int bytesRead = ba.read(b, offset, length);

        seekPosition += bytesRead;

        if (bytesRead < length) {
            if (seekPosition != realPosition) {
                file.seek(seekPosition);
            }

            file.readFully(b, offset + bytesRead, length - bytesRead);

            seekPosition += (length - bytesRead);
            realPosition = seekPosition;
        }
    }

    public void write(byte[] b, int off, int len) throws IOException {

        if (realPosition != seekPosition) {
            file.seek(seekPosition);
        }

        if (seekPosition >= bufferOffset
                && seekPosition < bufferOffset + buffer.length) {
            bufferDirty = true;
        }

        file.write(b, off, len);

        seekPosition += len;
        realPosition = seekPosition;
    }

    public void writeInt(int i) throws IOException {

        if (realPosition != seekPosition) {
            file.seek(seekPosition);
        }

        if (seekPosition >= bufferOffset
                && seekPosition < bufferOffset + buffer.length) {
            bufferDirty = true;
        }

        file.writeInt(i);

        seekPosition += 4;
        realPosition = seekPosition;
    }

    public void writeLong(long i) throws IOException {

        if (realPosition != seekPosition) {
            file.seek(seekPosition);
        }

        if (seekPosition >= bufferOffset
                && seekPosition < bufferOffset + buffer.length) {
            bufferDirty = true;
        }

        file.writeLong(i);

        seekPosition += 8;
        realPosition = seekPosition;
    }

    public void close() throws IOException {
        Trace.printSystemOut("cache hit " + cacheHit);
        file.close();
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public boolean wasNio() {
        return false;
    }
}
