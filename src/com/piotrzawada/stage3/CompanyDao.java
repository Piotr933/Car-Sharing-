package com.piotrzawada.stage3;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {
    void createTable();
    List<Company> companies() throws SQLException;
    void insert() throws SQLException;
}
