package com.piotrzawada.stage3;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    CompanyDaoImpl companyDao;
    CarDaoImpl carDao;

    public Menu(CompanyDaoImpl companyDao, CarDaoImpl carDao) {
        this.companyDao = companyDao;
        this.carDao = carDao;
    }

    protected void mainMenu() throws SQLException {

        for (;;) {
            System.out.println("1. Log in as a manager\n0. Exit");
            switch (choseOption()) {
                case "1":
                    subMenu();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong option");
            }
        }
    }

    private void subMenu() throws SQLException {

        for (;;) {
            System.out.println("1. Company list\n2. Create a company\n0. Back");
            switch (choseOption()) {
                case "1" :
                    printCompanyList(companyDao.companies());
                    if (!companyDao.companies().isEmpty()) {
                        companyMenu();}
                    break;
                case "2" :
                    companyDao.insert();
                    break;
                case "0" :
                    mainMenu();
                default :
                    System.out.println("Wrong option");
            }
        }
    }

    private void companyMenu() throws SQLException {
        int choseOption = Integer.parseInt(choseOption());
        if (choseOption == 0) {
            subMenu();}
        else { List<Company> companies = companyDao.companies();
            Company company = companies.get(choseOption - 1);
            carMenu(company);}
    }

    private void carMenu(Company company) throws SQLException {

        for(;;) {
            System.out.println("'" + company.getName() + "'" + "company\n1. Car list");
            System.out.println("2. Create a car\n 0. Back");
            switch (choseOption()){
                case "1" :
                    printCarList(carDao.cars(company.getId()));
                    break;
                case "2" :
                    carDao.insertCar(company.getId());
                    break;
                case "0" :
                    subMenu();
                    break;
                default:
                    System.out.println("Wrong option");
            }
        }
    }

    private String choseOption () {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private void printCompanyList(List<Company> list) {

        if(list.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Choose a company:");
            for (Company company : list) {
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println("0. Back");
        }
    }
    private void printCarList(List<Car> list) {
        int count = 1;

        if (list.isEmpty()) {
            System.out.println("The car list is empty!");

        } else {
            System.out.println("Car list:");
            for (Car car : list) {
                System.out.println(count + ". " + car.getNAME());
                count++;
            }
        }
    }
}
