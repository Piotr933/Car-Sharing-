package com.piotrzawada.car_sharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class CarDaoImpl implements CarDao {

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

    public List<Car> availableCars(int companyID) throws  SQLException {
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT ID,NAME,COMPANY_ID " +
                "FROM CAR " +
                "WHERE COMPANY_ID = " + companyID +
                " AND ID NOT IN (SELECT RENTED_CAR_ID FROM CUSTOMER " +
                "WHERE RENTED_CAR_ID IS NOT NULL)";
        List<Car> cars = new ArrayList<>();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            cars.add(new Car(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
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

    @Override
    public Car getCar(int id) throws SQLException {
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM CAR WHERE ID = " + id;
        ResultSet rs = statement.executeQuery(sql);
        Car car = null;
        while (rs.next()) {
            car = new Car(rs.getInt(1), rs.getString(2), rs.getInt(3));
        }
        return car;
    }
}
