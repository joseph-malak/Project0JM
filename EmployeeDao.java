package com.demo2;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {

    void Deactivate(int account_id) throws SQLException;

    void Activate(int account_id) throws SQLException;

     void IdCusotomer(int cust_id) throws SQLException;

    boolean getEmployeeLogin(String user, String password) throws SQLException;
}
