package com.ordecon.foresight.embeddedjdbc;

import java.sql.*;

/**
 * Created by: Ivan Stojic
 */

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Testing Foresight Embedded");

        Class.forName("com.ordecon.foresight.embeddedjdbc.FSEDriver");
        Connection con = DriverManager.getConnection("foresight://test");

        System.out.println("\nFull two-table cartesian product");
        testQuery(con, "person,phone|*");

        System.out.println("\nTwo table join");
        testQuery(con, "person,phone|*|person.username=phone.username");
    }

    private static void testQuery(Connection con, String sql) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i=0; i<rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i+1) + "(" + rsmd.getTableName(i+1) + ")\t");
        }
        System.out.println("\n===================================================");

        while (rs.next()) {
            for (int i=0; i<rsmd.getColumnCount(); i++) {
                System.out.print(rs.getObject(i+1) + "\t");
            }
            System.out.println("");
        }
    }
}
