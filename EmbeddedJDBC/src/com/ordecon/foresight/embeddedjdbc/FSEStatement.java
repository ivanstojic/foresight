package com.ordecon.foresight.embeddedjdbc;

import com.ordecon.foresight.abstractjdbc.AbstractStatement;
import com.ordecon.foresight.coredb.strategies.Strategy;
import com.ordecon.foresight.coredb.objects.Instance;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by: Ivan Stojic
 */

public class FSEStatement extends AbstractStatement {
    private Instance instance;

    public FSEStatement(Instance instance) {
        this.instance = instance;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        return new FSEResultSet(instance, sql);
    }
}
