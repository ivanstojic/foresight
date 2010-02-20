package com.ordecon.foresight.coredb.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** <p>Parser for a crappy pipe-separated database query language. It is meant to be used with Foresight until
 * a proper ANSI SQL parser can be developed.</p>
 *
 * The format is simple:
 *
 * <i>table</i>[,table...]|
 * 
 * @author Ivan Stojic
 */

public class CrappyParser implements QueryParser {
    private List<String> data = new ArrayList<String>();

    public CrappyParser(String expression) {
        data.addAll(Arrays.asList(expression.split("\\|")));
    }

    public List<String> getInvolvedTables() {
        return Arrays.asList(data.get(0).split(","));
    }

    public List<String> getInvolvedFields() {
        return Arrays.asList(data.get(1).split(","));
    }

    public List<String> getConditions() {
        if (data.size() >= 3) {
            return Arrays.asList(data.get(2).split(","));
        } else {
            return new ArrayList<String>();
        }
    }
}
