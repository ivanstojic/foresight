package com.ordecon.foresight.coredb.objects.tables;

import com.ordecon.foresight.coredb.objects.Column;
import com.ordecon.foresight.coredb.objects.Table;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/** Table instance which enumerates all the system properties in the current environment.
 * 
 * @author Ivan Stojic
 */

public class UserSystemProperties implements Table {
    public static String TABLE_NAME = "sys_properties";
    private static List<Column> columns;

    static {
        Column c;
        columns = new ArrayList<Column>();

        c = new Column("property_key", Types.VARCHAR, TABLE_NAME);
        columns.add(c);

        c = new Column("property_value", Types.VARCHAR, TABLE_NAME);
        columns.add(c);
    }

    public int getType() {
        return Table.TYPE_INTERNAL;
    }

    public String getName() {
        return TABLE_NAME;
    }

    public List<Column> getColumns() {
        return columns;
    }

    /* Does this column exist in the table */
    public boolean hasColumn(String name) {
        for (Column column : columns) {
            if (column.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public Column getColumn(String name) {
        for (Column column : columns) {
            if (column.getName().equals(name)) {
                return column;
            }
        }

        return null;
    }

    /* Get total number of rows in the table. */
    public int getRowCount() {
        return System.getProperties().size();
    }

    /* Get whole table. */
    public Object[][] getRowData() {
        Object[][] data = new Object[getRowCount()][];
        int position = -1;

        Enumeration e = System.getProperties().keys();
        while (e.hasMoreElements()) {
            position++;
            String s = (String) e.nextElement();
            data[position] = new Object[]{s, System.getProperties().getProperty(s)};
        }

        return data;
    }
}
