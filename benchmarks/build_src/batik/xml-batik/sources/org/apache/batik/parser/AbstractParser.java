/*

   Copyright 2001-2003  The Apache Software Foundation 

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */
package org.apache.batik.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Locale;
import java.util.MissingResourceException;

import org.apache.batik.i18n.LocalizableSupport;
import org.apache.batik.util.io.NormalizingReader;
import org.apache.batik.util.io.StreamNormalizingReader;
import org.apache.batik.util.io.StringNormalizingReader;

/**
 * This class is the superclass of all parsers. It provides localization
 * and error handling methods.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id: AbstractParser.java,v 1.10 2004/08/18 07:14:45 vhardy Exp $
 */
public abstract class AbstractParser implements Parser {

    /**
     * The default resource bundle base name.
     */
    public final static String BUNDLE_CLASSNAME =
	"org.apache.batik.parser.resources.Messages";

    /**
     * The error handler.
     */
    protected ErrorHandler errorHandler = new DefaultErrorHandler();

    /**
     * The localizable support.
     */
    protected LocalizableSupport localizableSupport =
        new LocalizableSupport(BUNDLE_CLASSNAME, 
                               AbstractParser.class.getClassLoader());

    /**
     * The normalizing reader.
     */
    protected NormalizingReader reader;

    /**
     * The current character.
     */
    protected int current;

    /**
     * Returns the current character value.
     */
    public int getCurrent() {
	return current;
    }

    /**
     * Implements {@link org.apache.batik.i18n.Localizable#setLocale(Locale)}.
     */
    public  void setLocale(Locale l) {
	localizableSupport.setLocale(l);
    }

    /**
     * Implements {@link org.apache.batik.i18n.Localizable#getLocale()}.
     */
    public Locale getLocale() {
        return localizableSupport.getLocale();
    }

    /**
     * Implements {@link
     * org.apache.batik.i18n.Localizable#formatMessage(String,Object[])}.
     */
    public String formatMessage(String key, Object[] args)
        throws MissingResourceException {
        return localizableSupport.formatMessage(key, args);
    }

    /**
     * Allow an application to register an error event handler.
     *
     * <p>If the application does not register an error event handler,
     * all error events reported by the parser will cause an exception
     * to be thrown.
`     *
     * <p>Applications may register a new or different handler in the
     * middle of a parse, and the parser must begin using the new
     * handler immediately.</p>
     * @param handler The error handler.
     */
    public void setErrorHandler(ErrorHandler handler) {
	errorHandler = handler;
    }

    /**
     * Parses the given reader
     */
    public void parse(Reader r) throws ParseException {
        try {
            reader = new StreamNormalizingReader(r);
            doParse();
        } catch (IOException e) {
            errorHandler.error
                (new ParseException
                 (createErrorMessage("io.exception", null), e));
        }
    }

    /**
     * Parses the given input stream. If the encoding is null,
     * ISO-8859-1 is used.
     */
    public void parse(InputStream is, String enc) throws ParseException {
        try {
            reader = new StreamNormalizingReader(is, enc);
            doParse();
        } catch (IOException e) {
            errorHandler.error
                (new ParseException
                 (createErrorMessage("io.exception", null), e));
        }
    }

    /**
     * Parses the given string.
     */
    public void parse(String s) throws ParseException {
        try {
            reader = new StringNormalizingReader(s);
            doParse();
        } catch (IOException e) {
            errorHandler.error
                (new ParseException
                 (createErrorMessage("io.exception", null), e));
        }
    }

    /**
     * Method responsible for actually parsing data after AbstractParser
     * has initialized itself.
     */
    protected abstract void doParse()
        throws ParseException, IOException;

    /**
     * Signals an error to the error handler.
     * @param key The message key in the resource bundle.
     * @param args The message arguments.
     */
    protected void reportError(String key, Object[] args)
        throws ParseException {
        errorHandler.error(new ParseException(createErrorMessage(key, args),
                                              reader.getLine(),
                                              reader.getColumn()));
    }

    /**
     * Returns a localized error message.
     * @param key The message key in the resource bundle.
     * @param args The message arguments.
     */
    protected String createErrorMessage(String key, Object[] args) {
        try {
            return formatMessage(key, args);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Returns the resource bundle base name.
     * @return BUNDLE_CLASSNAME.
     */
    protected String getBundleClassName() {
        return BUNDLE_CLASSNAME;
    }

    /**
     * Skips the whitespaces in the current reader.
     */
    protected void skipSpaces() throws IOException {
        for (;;) {
            switch (current) {
            default:
                return;
            case 0x20:
            case 0x09:
            case 0x0D:
            case 0x0A:
            }
            current = reader.read();
        }
    }

    /**
     * Skips the whitespaces and an optional comma.
     */
    protected void skipCommaSpaces() throws IOException {
        wsp1: for (;;) {
	    switch (current) {
	    default:
		break wsp1;
	    case 0x20:
	    case 0x9:
	    case 0xD:
	    case 0xA:
	    }
	    current = reader.read();
	}
	if (current == ',') {
            wsp2: for (;;) {
		switch (current = reader.read()) {
		default:
		    break wsp2;
		case 0x20:
		case 0x9:
		case 0xD:
		case 0xA:
		}
	    }
	}
    }
}
