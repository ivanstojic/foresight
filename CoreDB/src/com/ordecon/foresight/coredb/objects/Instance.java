package com.ordecon.foresight.coredb.objects;

import com.ordecon.foresight.coredb.parsers.CrappyParser;
import com.ordecon.foresight.coredb.parsers.QueryParser;
import com.ordecon.foresight.coredb.strategies.Strategy;
import com.ordecon.foresight.coredb.strategies.LeftDeepCrossJoin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Ivan Stojic
 */

public class Instance {
    private String name;
    private List<Schema> schemas = new ArrayList<Schema>();
    private final LeftDeepCrossJoin strategy;

    public Instance(String name) {
        this.name = name;
        strategy = new LeftDeepCrossJoin(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Schema> getSchemas() {
        return schemas;
    }

    public void setSchemas(List<Schema> schemas) {
        this.schemas = schemas;
    }

    public Schema getSchemaByName(String name) {
        for (Schema s : schemas) {
            if (s.getName().equals(name)) {
                return s;
            }
        }

        return null;
    }

    public Object[][] executeQuery(String query) {
        return strategy.executeQuery(query);
    }

    public Column[] getQueryMetaData(String query) {
        return strategy.getQueryMetaData(query);
    }
}
