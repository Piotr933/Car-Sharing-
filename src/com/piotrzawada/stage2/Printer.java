package com.piotrzawada.stage2;

import java.util.List;

public class Printer {
    public static void printString(String txt) {
        System.out.println(txt);
    }

    public static void printCompanyList(List<Company> list) {

        if(list.isEmpty()) {
            System.out.println("The company list is empty!");
        }

        for (Company company : list) {
            System.out.println(company.getId() + ". " + company.getName());
        }
    }

    public static void printMenuOpt(String menu) {

        switch (menu) {

            case "main" : {
                System.out.println("1. Log in as a manager");
                System.out.println("0. Exit");
                break;
            }

            case "sub" : {
                System.out.println("1. Company list");
                System.out.println("2. Create a company");
                System.out.println("0. Back");
                break;
            }
        }
    }
}
