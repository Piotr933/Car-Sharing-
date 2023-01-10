package com.piotrzawada.stage3;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {
    void creteTable();
    List<Car> cars(int companyID) throws SQLException;
    void insertCar(int companyID) throws SQLException;
}
