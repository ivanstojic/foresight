package com.ordecon.foresight.coredb.strategies;

import com.ordecon.foresight.coredb.objects.Column;

/**
 * Created by: Ivan Stojic
 */
public interface Strategy {
    /* Core query execution logic for the database */
    Object[][] executeQuery(String query);/* Get result metadata */

    Column[] getQueryMetaData(String query);
}
