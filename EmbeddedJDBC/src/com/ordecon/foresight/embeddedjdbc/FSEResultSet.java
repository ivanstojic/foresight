package com.ordecon.foresight.embeddedjdbc;

import com.ordecon.foresight.abstractjdbc.AbstractResultSet;
import com.ordecon.foresight.coredb.objects.Instance;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by: Ivan Stojic
 */

public class FSEResultSet extends AbstractResultSet {
    private Instance instance;
    private String query;
    private Object[][] resultSet;
    private int position = -1;
    private ResultSetMetaData metaData;

    public FSEResultSet(Instance instance, String query) {
        this.instance = instance;
        this.query = query;
        resultSet = this.instance.executeQuery(query);
        position = -1;
    }

    public boolean next() throws SQLException {
        position++;

        return position < resultSet.length;
    }

    public String getString(int columnIndex) throws SQLException {
        return (String) resultSet[position][columnIndex-1];
    }

    public int getInt(int columnIndex) throws SQLException {
        return ((Integer) resultSet[position][columnIndex-1]);
    }

    public boolean isBeforeFirst() throws SQLException {
        return position == -1;
    }

    public boolean isAfterLast() throws SQLException {
        return position == resultSet.length;
    }

    public boolean isFirst() throws SQLException {
        return position == 0;
    }

    public boolean isLast() throws SQLException {
        return position == resultSet.length - 1;
    }

    public int getRow() throws SQLException {
        return position + 1;
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return new FSEResultSetMetaData(instance, query);
    }

    public Object getObject(int columnIndex) throws SQLException {
        return resultSet[position][columnIndex-1];
    }
}
