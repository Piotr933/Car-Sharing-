package com.piotrzawada.stage3;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CompanyDaoImpl companyDaoImpl  = new CompanyDaoImpl();
        CarDaoImpl carDaoImp = new CarDaoImpl();
        companyDaoImpl.createTable();
        carDaoImp.creteTable();
        Menu menu = new Menu(companyDaoImpl, carDaoImp);
        menu.mainMenu();
    }
}