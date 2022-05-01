/*
 * Jython Database Specification API 2.0
 *
 * $Id: QueueClosedException.java,v 1.2 2005/02/23 04:26:21 bzimmer Exp $
 *
 * Copyright (c) 2001 brian zimmer <bzimmer@ziclix.com>
 *
 */
package com.ziclix.python.sql.util;

/**
 * This exception is thrown when the queue is closed and an operation is attempted.
 *
 * @author brian zimmer
 * @version $Revision: 1.2 $
 */
public class QueueClosedException extends RuntimeException {

    /**
     * Constructor QueueClosedException
     */
    public QueueClosedException() {
        super();
    }

    /**
     * Constructor QueueClosedException
     *
     * @param msg
     */
    public QueueClosedException(String msg) {
        super(msg);
    }
}
