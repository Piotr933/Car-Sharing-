package com.piotrzawada.car_sharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class CompanyDaoImpl implements CompanyDao {

    @Override
    public List<Company> companies() throws SQLException {
        List<Company> companies = new ArrayList<>();
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM COMPANY";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            companies.add(new Company(rs.getInt(1), rs.getString(2)));
        }

        connection.close();
        return companies;
    }
    @Override
    public void insert() throws SQLException {
        Connection connection = DB_Manager.getConnection();
        Scanner scanner = new Scanner(System.in);
        Statement statement = connection.createStatement();
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        String sql = "INSERT INTO COMPANY (Name)" +
                "VALUES(" + "'" + name + "'" + ")";
        statement.executeUpdate(sql);
        statement.close();
        connection.close();
    }

    public Company getCompanyById(int id) throws SQLException {
        Connection connection = DB_Manager.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM COMPANY WHERE id = " +id;
        ResultSet rs = statement.executeQuery(sql);
        Company company = null;
        while (rs.next()) {
            company = new Company(rs.getInt(1), rs.getString(2));
        }

        return company;
    }
}
