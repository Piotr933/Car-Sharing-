package com.piotrzawada.stage2;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        CompanyDaoImpl companyDao  = new CompanyDaoImpl();
        companyDao.createTable();
        Menu menu = new Menu(companyDao);
        menu.mainMenu();
    }
}
