package com.ordecon.foresight.coredb.objects;

import java.util.List;

/**
 * @author Ivan Stojic
 */

public interface Table {
    public static int TYPE_INTERNAL = 1;
    public static int TYPE_MEMORY = 2;
    public static int TYPE_PERSISTENT = 4;

    public int getType();
    public String getName();

    /* Get column data of this table. */
    public List<Column> getColumns();

    /* Does this column exist in the table */
    public boolean hasColumn(String name);

    /** Get the column metadata for a given column name.
     *
     * @param name Name of the column.
     * @return An instance of the Column class holding column metadata or null if the column does not exist in this table.
     */
    public Column getColumn(String name);

    /* Get total number of rows in the table. */
    public int getRowCount();

    /* Get whole table. */
    public Object[][] getRowData();
}
