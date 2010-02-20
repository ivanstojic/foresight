package com.ordecon.foresight.coredb.parsers;

import java.util.List;

/**
 * Created by: Ivan Stojic
 */

public interface QueryParser {
    List<String> getInvolvedTables();

    List<String> getInvolvedFields();

    List<String> getConditions();
}
