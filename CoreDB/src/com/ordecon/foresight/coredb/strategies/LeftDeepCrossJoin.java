package com.ordecon.foresight.coredb.strategies;

import com.ordecon.foresight.coredb.objects.Column;
import com.ordecon.foresight.coredb.objects.Instance;
import com.ordecon.foresight.coredb.objects.Schema;
import com.ordecon.foresight.coredb.objects.Table;
import com.ordecon.foresight.coredb.parsers.CrappyParser;
import com.ordecon.foresight.coredb.parsers.QueryParser;

import java.util.ArrayList;
import java.util.List;

/** Simple and stupid query execution strategy. Uses left deep cross joining of all the tables, and
 * then performs the filtering of the resulting dataset.
 * @author Ivan Stojic
 */
public class LeftDeepCrossJoin implements Strategy {
    private Instance instance;

    public LeftDeepCrossJoin(Instance instance) {
        this.instance = instance;
    }

    /* Core query execution logic for the database */
    public Object[][] executeQuery(String query) {
        QueryParser cp = new CrappyParser(query);

        // Find and validate all tables and fields
        Schema s = instance.getSchemaByName("default");
        int fieldcount = 0;
        int rowcount = 0;

        for (String name : cp.getInvolvedTables()) {
            Table t = s.getTableByName(name);

            if (t == null) {
                throw new IllegalArgumentException("Table " + name + " not found in schema.");
            }

            fieldcount += t.getColumns().size();
            if (rowcount == 0) {
                rowcount = t.getRowCount();
            } else {
                rowcount *= t.getRowCount();
            }
        }

        // Fetch and collate the data
        Object[][] data = new Object[rowcount][fieldcount];
        int offset = 0;
        int freq = 1;

        for (String name : cp.getInvolvedTables()) {
            Table t = s.getTableByName(name);

            collateData(data, t, freq, offset);
            freq *= t.getRowCount();

            offset += t.getColumns().size();
        }

        // Filter the data
        if (cp.getConditions().size() > 0) {
            Column[] columns = getQueryMetaData(query);

            for (String condition : cp.getConditions()) {
                String[] leftright = condition.split("=");
                String[] left = leftright[0].split("\\.");
                String[] right = leftright[1].split("\\.");

                int leftidx = -1, rightidx = -1;
                int index = -1;

                // find left and right field indices
                for (Column c : columns) {
                    index++;
                    if (c.getName().equals(left[1]) && c.getGroupName().equals(left[0])) {
                        leftidx = index;
                    }

                    if (c.getName().equals(right[1]) && c.getGroupName().equals(right[0])) {
                        rightidx = index;
                    }
                }

                // check if both fields exist
                if (leftidx == -1 || rightidx == -1) {
                    throw new IllegalArgumentException("Cannot find one of the condition fields");
                }

                List<Object> temp = new ArrayList<Object>();

                for (Object[] row : data) {
                    if (row[leftidx].equals(row[rightidx]))  {
                        temp.add(row);
                    }
                }

                data = new Object[temp.size()][];
                temp.toArray(data);
            }
        }

        return data;
    }


    /* Get result metadata */
    public Column[] getQueryMetaData(String query) {
        QueryParser cp = new CrappyParser(query);
        List<Column> output = new ArrayList<Column>();

        Schema s = instance.getSchemaByName("default");

        for (String name : cp.getInvolvedTables()) {
            Table t = s.getTableByName(name);

            output.addAll(t.getColumns());
        }

        Column[] temp = new Column[output.size()];
        output.toArray(temp);

        return temp;
    }

    /* Collect data from various tables. Warning - currently only does an ugly crossjoin */
    private void collateData(Object[][] data, Table source, int frequency, int fieldoffset) {
        Object[][] input = source.getRowData();
        int srcidx = -1;

        for (int i = 0; i < data.length; i++) {
            if (i % frequency == 0) {
                srcidx = (srcidx + 1) % input.length;
            }

            System.arraycopy(input[srcidx], 0, data[i], fieldoffset, input[srcidx].length);
        }
    }
}
