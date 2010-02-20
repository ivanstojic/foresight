package com.ordecon.foresight.embeddedjdbc;

import com.ordecon.foresight.abstractjdbc.AbstractConnection;
import com.ordecon.foresight.coredb.InstanceFactory;
import com.ordecon.foresight.coredb.objects.Instance;
import com.ordecon.foresight.coredb.strategies.Strategy;

import java.sql.*;
import java.util.Properties;

/**
 * Created by: Ivan Stojic
 */

public class FSEConnection extends AbstractConnection {
    private String instanceName;
    private Instance instance;

    public FSEConnection(String instanceName) {
        this.instanceName = instanceName;
        instance = InstanceFactory.getInstance(instanceName);
    }

    public Statement createStatement() throws SQLException {
        return new FSEStatement(instance);
    }
}
