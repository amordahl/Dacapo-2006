/*
 * Jython Database Specification API 2.0
 *
 * $Id: MySQLDataHandler.java,v 1.4 2005/05/16 06:43:33 otmarhumbel Exp $
 *
 * Copyright (c) 2001 brian zimmer <bzimmer@ziclix.com>
 *
 */
package com.ziclix.python.sql.handler;

import com.ziclix.python.sql.DataHandler;
import org.python.core.PyFile;
import org.python.core.PyObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * MySQL specific data handling.
 *
 * @author brian zimmer
 * @author last revised by $Author: otmarhumbel $
 * @version $Revision: 1.4 $
 */
public class MySQLDataHandler extends RowIdHandler {

  /**
   * Decorator for handling MySql specific issues.
   *
   * @param datahandler the delegate DataHandler
   */
  public MySQLDataHandler(DataHandler datahandler) {
    super(datahandler);
  }

  protected String getRowIdMethodName() {
    return "getLastInsertID";
  }

  /**
   * Handle LONGVARCHAR.
   */
  public void setJDBCObject(PreparedStatement stmt, int index, PyObject object, int type) throws SQLException {

    if (DataHandler.checkNull(stmt, index, object, type)) {
      return;
    }

    switch (type) {

      case Types.LONGVARCHAR:
        String varchar;
        if (object instanceof PyFile) {
          varchar = ((PyFile) object).read();
        } else {
          varchar = (String) object.__tojava__(String.class);
        }
        InputStream stream = new ByteArrayInputStream(varchar.getBytes());

        stream = new BufferedInputStream(stream);

        stmt.setAsciiStream(index, stream, varchar.length());
        break;

      default :
        super.setJDBCObject(stmt, index, object, type);
        break;
    }
  }
}
