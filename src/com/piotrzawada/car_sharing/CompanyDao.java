package com.piotrzawada.car_sharing;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {

    List<Company> companies() throws SQLException;

    void insert() throws SQLException;

    Company getCompanyById(int id) throws SQLException;

}
