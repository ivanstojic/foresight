package com.ordecon.foresight.coredb.objects.tables;

import com.ordecon.foresight.coredb.objects.Column;
import com.ordecon.foresight.coredb.objects.Schema;
import com.ordecon.foresight.coredb.objects.Table;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Ivan Stojic
 */

public class UserTables implements Table {
    public static String TABLE_NAME = "user_tables";
    public Schema parentSchema;
    private static List<Column> columns;

    static {
        columns = new ArrayList<Column>();

        Column c;

        c = new Column("table_name", Types.VARCHAR, "user_tables");
        columns.add(c);

        c = new Column("num_rows", Types.NUMERIC, "user_tables");
        columns.add(c);
    }

    public UserTables(Schema parentSchema) {
        this.parentSchema = parentSchema;
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

    public int getRowCount() {
        return parentSchema.getTables().size();
    }

    /* Get whole table. */
    public Object[][] getRowData() {
        Object[][] data = new Object[parentSchema.getTables().size()][];
        int i = 0;

        for (Table table : parentSchema.getTables()) {
            data[i] = new Object[]{table.getName(), table.getRowCount()};
            i++;
        }

        return data;
    }
}
