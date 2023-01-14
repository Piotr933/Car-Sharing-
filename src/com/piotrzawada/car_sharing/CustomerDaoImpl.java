package com.piotrzawada.car_sharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class CustomerDaoImpl implements CustomerDao {

    @Override
    public List<Customer> customers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM CUSTOMER";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            customers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }

        connection.close();
        return customers;
    }


    @Override
    public void insertCustomer() throws SQLException {
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        String sql = "INSERT INTO CUSTOMER (NAME)" +
                "VALUES(" + "'" + name + "'" + ")";
        statement.executeUpdate(sql);
        System.out.println("The customer was added!");
        statement.close();
        connection.close();
    }

    @Override
    public void rentCar(Customer customer, Car car) throws SQLException {
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        String sql = "UPDATE CUSTOMER " +
                "SET RENTED_CAR_ID = " + car.getID() +
                " WHERE ID = " + customer.getId();
        System.out.println("You rented '" + car.getNAME() + "'");
        statement.executeUpdate(sql);
        statement.close();
    }


    @Override
    public void returnCar(Customer customer, Car car) throws SQLException {
        if (car == null) {
            System.out.println("You didn't rent a car!");
        } else {
            Connection connection = DB_Manager.getConnection();
            Statement statement = connection.createStatement();
            String sql = "UPDATE CUSTOMER " +
                    "SET RENTED_CAR_ID = NULL"
                    + " WHERE ID = " + customer.getId();
            statement.executeUpdate(sql);
            System.out.println("You've returned a rented car!");
        }
    }
}
