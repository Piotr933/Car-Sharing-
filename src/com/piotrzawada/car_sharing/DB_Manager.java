package com.piotrzawada.car_sharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Manager {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:file:./src/carsharing/db/carsharing";
    static final String companyTable = "CREATE TABLE IF NOT EXISTS COMPANY" +
            "(ID INT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(255) NOT NULL UNIQUE)";
    static final String carTable = "CREATE TABLE IF NOT EXISTS CAR" +
            "(ID INT PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR(255) NOT NULL UNIQUE," +
            "COMPANY_ID INT NOT NULL," +
            "CONSTRAINT FK_COMPANY FOREIGN KEY(COMPANY_ID)" +
            "REFERENCES COMPANY(id))";
    static final String customerTable = "CREATE TABLE IF NOT EXISTS CUSTOMER" +
            "(ID INT PRIMARY KEY AUTO_INCREMENT," +
            "NAME VARCHAR(255) NOT NULL UNIQUE," +
            "RENTED_CAR_ID INT DEFAULT NULL," +
            "CONSTRAINT FK_RENTED_CAR_ID FOREIGN KEY(RENTED_CAR_ID)" +
            "REFERENCES CAR(ID))";

    public static Connection getConnection() {
        Connection connection;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);
            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void createTables() {
        Connection connection = DB_Manager.getConnection();
        Statement statement;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(companyTable);
            statement.executeUpdate(carTable);
            statement.executeUpdate(customerTable);
            statement.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
