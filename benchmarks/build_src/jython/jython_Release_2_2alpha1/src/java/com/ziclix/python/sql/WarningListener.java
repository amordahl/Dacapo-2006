/*
 * Jython Database Specification API 2.0
 *
 * $Id: WarningListener.java,v 1.2 2005/02/23 04:26:18 bzimmer Exp $
 *
 * Copyright (c) 2001 brian zimmer <bzimmer@ziclix.com>
 *
 */
package com.ziclix.python.sql;

public interface WarningListener {

    /**
     * A callback for any SQLWarnings encountered by the source.
     *
     * @param event An event instance with the source and warning.
     */
    public void warning(WarningEvent event);
}
