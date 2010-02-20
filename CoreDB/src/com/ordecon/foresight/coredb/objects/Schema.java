package com.ordecon.foresight.coredb.objects;

import com.ordecon.foresight.coredb.objects.tables.UserTables;
import com.ordecon.foresight.coredb.objects.tables.UserSystemProperties;
import com.ordecon.foresight.coredb.objects.tables.TextfileTable;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by: Ivan Stojic
 */

public class Schema {
    private String name;
    private List<Table> tables = new ArrayList<Table>();

    public Schema(String name) {
        this.name = name;

        // Add internal tables
        tables.add(new UserTables(this));
        tables.add(new UserSystemProperties());

        // Add testing tables - currently contains person and phone tables
        tables.add(new TextfileTable("TestTables/person.txt", "person"));
        tables.add(new TextfileTable("TestTables/phone.txt", "phone"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public Table getTableByName(String name) {
        for (Table t : tables) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
