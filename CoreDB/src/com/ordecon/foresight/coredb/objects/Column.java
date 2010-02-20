package com.ordecon.foresight.coredb.objects;

/**
 * Created by: Ivan Stojic
 */

public class Column {
    private String groupName;
    private String name;
    private int type;

    public Column(String name, int type, String group) {
        this.name = name;
        this.type = type;
        this.groupName = group;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
