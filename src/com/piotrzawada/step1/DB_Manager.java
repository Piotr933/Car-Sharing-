package com.piotrzawada.step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DB_Manager {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:./src/carsharing/db/carsharing";

    protected static void init() {
        Connection connection;
        Statement statement;
        String table = "CREATE TABLE COMPANY" +
                "(ID INT," +
                "NAME VARCHAR(255))";

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS COMPANY");
            statement.execute(table);
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
