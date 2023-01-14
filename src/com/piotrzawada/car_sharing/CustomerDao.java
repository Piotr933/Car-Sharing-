package com.piotrzawada.car_sharing;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    List<Customer> customers() throws SQLException;

    void insertCustomer() throws SQLException;

    void rentCar(Customer customer, Car car) throws SQLException;

    void returnCar(Customer customer, Car car) throws SQLException;

}
