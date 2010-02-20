package com.ordecon.foresight.coredb.objects.tables;

import com.ordecon.foresight.coredb.objects.Table;
import com.ordecon.foresight.coredb.objects.Column;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.sql.Types;

/**
 * Created by: Ivan Stojic
 */

public class TextfileTable implements Table {
    private String tableName;
    private List<Column> columns = new ArrayList<Column>();
    private String[][] data;

    public TextfileTable(String fileName, String tableName) {
        this.tableName = tableName;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            List<String[]> items = new ArrayList<String[]>();

            // First line contains field names
            line = br.readLine();
            for (String s : line.split("\t")) {
                Column c = new Column(s, Types.VARCHAR, tableName);
                columns.add(c);
            }

            // Data is in the other lines
            while ((line = br.readLine()) != null) {
                items.add(line.split("\t"));
            }

            data = new String[items.size()][];
            items.toArray(data);

            br.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getType() {
        return Table.TYPE_INTERNAL;
    }

    public String getName() {
        return tableName;
    }

    /* Get column data of this table. */
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
        return data.length;
    }

    /* Get whole table. */
    public Object[][] getRowData() {
        return data;
    }
}
