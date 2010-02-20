package com.ordecon.foresight.embeddedjdbc;

import com.ordecon.foresight.abstractjdbc.AbstractResultSetMetaData;
import com.ordecon.foresight.coredb.objects.Column;
import com.ordecon.foresight.coredb.objects.Instance;
import com.ordecon.foresight.coredb.strategies.Strategy;

import java.sql.SQLException;

/**
 * Created by: Ivan Stojic
 */

public class FSEResultSetMetaData extends AbstractResultSetMetaData {
    private Instance instance;
    private String query;
    private Column[] columns;

    public FSEResultSetMetaData(Instance instance, String query) {
        this.instance = instance;
        this.query = query;
    }

    private void prefetchColumnInfo() {
        if (columns == null) {
            columns = instance.getQueryMetaData(query);
        }
    }

    public int getColumnCount() throws SQLException {
        prefetchColumnInfo();

        return columns.length;
    }

    public String getColumnName(int column) throws SQLException {
        prefetchColumnInfo();

        return columns[column-1].getName();
    }

    public int getColumnType(int column) throws SQLException {
        prefetchColumnInfo();

        return columns[column-1].getType();
    }

    public String getColumnTypeName(int column) throws SQLException {
        return "UNDEFINED";
    }

    public String getTableName(int column) throws SQLException {
        return columns[column-1].getGroupName();
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
