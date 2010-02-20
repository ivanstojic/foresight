package com.ordecon.foresight.embeddedjdbc;

import com.ordecon.foresight.abstractjdbc.AbstractDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by: Ivan Stojic
 */

public class FSEDriver extends AbstractDriver {
    public static int MAJOR_VERSION = 0;
    public static int MINOR_VERSION = 1;

    static {
        FSEDriver driver = new FSEDriver();
        try {
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            System.err.println("SQLException during initialization of FSEDriver");
        }
    }

    public Connection connect(String url, Properties info) throws SQLException {
        System.out.println("Connection to " + url + " requested");

        if (acceptsURL(url)) {
            String instanceName = url.substring(12);
            return new FSEConnection(instanceName);
        } else {
            return null;
        }
    }

    public boolean acceptsURL(String url) throws SQLException {
        if (url != null && url.startsWith("foresight://")) {
            return true;
        }

        return false;
    }

    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    public int getMinorVersion() {
        return MINOR_VERSION;
    }
}
