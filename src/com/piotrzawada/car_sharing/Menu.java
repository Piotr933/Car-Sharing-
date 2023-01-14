package com.piotrzawada.car_sharing;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public final class Menu {

    CompanyDaoImpl companyDaoImp;
    CarDaoImpl carDaoImp;
    CustomerDaoImpl customerDaoImp;

    public Menu(CompanyDaoImpl companyDao, CarDaoImpl carDao, CustomerDaoImpl customerDao) {
        this.companyDaoImp = companyDao;
        this.carDaoImp = carDao;
        this.customerDaoImp = customerDao;
    }

    void mainMenu() throws SQLException {

        while (true) {
            System.out.println("1. Log in as a manager\n2. Log in as a customer");
            System.out.println("3. Create a customer\n0. Exit");

            switch (choseOption()) {
                case "1":
                    logAsManager();
                    break;
                case "2":
                    logAsCustomer();
                    break;
                case "3":
                    customerDaoImp.insertCustomer();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Wrong option");
            }
        }
    }

    private void logAsManager() throws SQLException {

        while (true) {
            System.out.println("1. Company list\n2. Create a company\n0. Back");
            switch (choseOption()) {
                case "1":
                    if (!companyDaoImp.companies().isEmpty()) {
                        companyMenu();
                    } else {
                        System.out.println("The company list is empty!");
                    }
                    break;
                case "2":
                    companyDaoImp.insert();
                    break;
                case "0":
                    mainMenu();
                default:
                    System.out.println("Wrong option");
            }
        }
    }

    private void logAsCustomer() throws SQLException {
        List<Customer> customers = customerDaoImp.customers();

        if (!customers.isEmpty()) {
            System.out.println("Choose a customer:");
            for (Customer customer : customers) {
                System.out.println(customer.getId() + ". " + customer.getName());
            }
        } else {
            System.out.println("The customer list is empty!");
        }

        System.out.println("0. Back");


        if (customers.isEmpty()) {
            mainMenu();
        }

        int choseOption = Integer.parseInt(choseOption());

        if (choseOption == 0) {
            mainMenu();
        } else {
            customerMenu(choseOption);
        }
    }

    private void customerMenu(int customerID) throws SQLException {

        while (true) {
            List<Customer> customers = customerDaoImp.customers();
            Customer customer = customers.get(customerID - 1);
            System.out.println("1. Rent a car\n2. Return a rented car");
            System.out.println("3. My rented car\n0. Back");
            switch (choseOption()) {
                case "1":
                    rentACar(customer);
                    break;
                case "2":
                    customerDaoImp.returnCar(customer, carDaoImp.getCar(customer.getRented_Car_Id()));
                    break;
                case "3":
                    myRentedCar(customer.getId());
                    break;
                case "0":
                    mainMenu();
                    break;

                default:
                    System.out.println("Wrong option");
            }
        }
    }

    private void companyMenu() throws SQLException {
        System.out.println("Choose a company:");

        for (Company company : companyDaoImp.companies()) {
            System.out.println(company.getId() + ". " + company.getName());
        }

        System.out.println("0. Back");
        int choseOption = Integer.parseInt(choseOption());

        if (choseOption == 0) {
            logAsManager();
        } else {
            List<Company> companies = companyDaoImp.companies();
            Company company = companies.get(choseOption - 1);
            carMenu(company);
        }
    }

    private void carMenu(Company company) throws SQLException {

        while (true) {
            System.out.println("'" + company.getName() + "'" + "company\n1. Car list");
            System.out.println("2. Create a car\n 0. Back");
            switch (choseOption()) {
                case "1":
                    printCarList(carDaoImp.cars(company.getId()));
                    break;
                case "2":
                    carDaoImp.insertCar(company.getId());
                    break;
                case "0":
                    logAsManager();
                    break;
                default:
                    System.out.println("Wrong option");
            }
        }
    }

    private void rentACar(Customer customer) throws SQLException {

        if (customer.getRented_Car_Id() != 0) {                  //checking if customer rented a car
            System.out.println("You've already rented a car!");
            customerMenu(customer.getId());
        }

        List<Company> companies = companyDaoImp.companies();       // time to choose renting company

        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
            customerMenu(customer.getId());
        } else {
            System.out.println("Choose a company");
            for (Company company : companies) {
                System.out.println(company.getId() + ". " + company.getName());
            }
            System.out.println("0. Back");
        }

        int chosenCompany = Integer.parseInt(choseOption());

        if (chosenCompany == 0) {
            customerMenu(customer.getId());
        } else {
            subMenuOfRentACar(companies, chosenCompany, customer);  // now check available cars
        }
    }

    private void subMenuOfRentACar(List<Company> companies, int chosenCompany, Customer customer) throws SQLException {
        List<Car> cars = carDaoImp.availableCars(companies.get(chosenCompany - 1).getId());
        if (cars.isEmpty()) {
            System.out.println("No available cars in the '" + companies.get(chosenCompany - 1).getName() +
                    "' company");
        } else {
            System.out.println("Choose a car: ");

            int count = 1;

            if (cars.isEmpty()) {
                System.out.println("The car list is empty!");

            } else {
                System.out.println("Car list:");
                for (Car car : cars) {
                    System.out.println(count + ". " + car.getNAME());
                    count++;
                }
            }

            int choseOption = Integer.parseInt(choseOption());

            if (choseOption == 0) {
                customerMenu(customer.getId());
            } else {
                Customer customer1 = customerDaoImp.customers().get(customer.getId() - 1);
                Car car = cars.get(choseOption - 1);
                customerDaoImp.rentCar(customer1, car); //rent a car
            }
        }
    }

    private void myRentedCar(int id) throws SQLException {
        Customer customer = customerDaoImp.customers().get(id - 1);
        Car car = carDaoImp.getCar(customer.getRented_Car_Id());
        System.out.println(customer.getRented_Car_Id() + " test");

        if (car == null) {
            System.out.println("You didn't rent a car!");
        } else {
            System.out.println("You rented car:");
            System.out.println(car.getNAME());
            System.out.println("Company: ");
            System.out.println(companyDaoImp.getCompanyById(car.getCOMPANY_ID()).getName());
        }
    }
    private String choseOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
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
