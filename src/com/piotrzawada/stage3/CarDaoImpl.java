package com.piotrzawada.stage3;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarDaoImpl implements CarDao {

    @Override
    public void creteTable() {
        Connection connection = DB_Manager.getConnection();
        Statement statement;
        String table = "CREATE TABLE CAR" +
                "(ID INT PRIMARY KEY AUTO_INCREMENT," +
                "NAME VARCHAR(255) NOT NULL UNIQUE," +
                "COMPANY_ID INT NOT NULL," +
                "CONSTRAINT FK_COMPANY FOREIGN KEY(COMPANY_ID)" +
                "REFERENCES COMPANY(id))";
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS CAR CASCADE");
            statement.execute(table);
            statement.close();
        } catch (
                SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Car> cars(int companyId) throws SQLException {
        List<Car> cars = new ArrayList<>();
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM CAR WHERE COMPANY_ID = " + companyId ;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            cars.add(new Car(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        connection.close();
        return cars;
    }

    @Override
    public void insertCar(int companyID) throws SQLException {
        Connection connection = DB_Manager.getConnection();
        Scanner scanner = new Scanner(System.in);
        Statement statement = connection.createStatement();
        System.out.println("Enter the car name:");
        String name = scanner.nextLine();
        String sql = "INSERT INTO CAR (NAME, COMPANY_ID)" +
                "VALUES(" + "'" + name + "', " + companyID + " )";
        statement.executeUpdate(sql);
        System.out.println("The car was added!");
        statement.close();
        connection.close();
    }
}
