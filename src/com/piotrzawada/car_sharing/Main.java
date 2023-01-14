package com.piotrzawada.car_sharing;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CompanyDaoImpl companyDaoImpl  = new CompanyDaoImpl();
        CarDaoImpl carDaoImpl = new CarDaoImpl();
        CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
        DB_Manager.createTables();
        Menu menu = new Menu(companyDaoImpl, carDaoImpl, customerDaoImpl);
        menu.mainMenu();
    }
}
