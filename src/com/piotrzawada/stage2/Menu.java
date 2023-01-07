package com.piotrzawada.stage2;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    CompanyDaoImpl companyDao;

    public Menu(CompanyDaoImpl companyDao) {
        this.companyDao = companyDao;
    }

    protected void mainMenu() throws SQLException {

        for (;;) {
            Printer.printMenuOpt("main");
            switch (choseOption()) {
                case "1":
                    subMenu();
                    break;
                case "0":
                    System.exit(0);
                default:
                    Printer.printString("Wrong option");
            }
        }
    }

    private void subMenu() throws SQLException {

        for (;;) {
            Printer.printMenuOpt("sub");
            switch (choseOption()) {
                case "1" :
                    Printer.printCompanyList(companyDao.companies());
                    break;
                case "2" :
                    companyDao.insert();
                    break;
                case "0" :
                    mainMenu();
                default :
                    Printer.printString("Wrong option");
            }
        }
    }
    private String choseOption () {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
