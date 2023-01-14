package com.piotrzawada.car_sharing;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {

    List<Car> cars(int companyID) throws SQLException;

    void insertCar(int companyID) throws SQLException;

    Car getCar(int id) throws SQLException;

}
