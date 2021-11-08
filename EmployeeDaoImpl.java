package com.demo2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    Connection connection;
    String input;

    public EmployeeDaoImpl() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public void Activate(int account_id) throws SQLException {
        String sql = "update accounts set activated = 'true' where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account_id);
        int count = preparedStatement.executeUpdate();
        if (count >0) {
            System.out.println("Updating system....");
        } else {
            System.out.println("An error has occurred");
        }
    }

    @Override
    public void Deactivate(int account_id) throws SQLException {
        String sql = "update accounts set activated = 'false' where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account_id);
        int count = preparedStatement.executeUpdate();
        if (count >0) {
            System.out.println("Updating system....");
        } else {
            System.out.println("An error has occurred");
        }
    }
    public void IdCusotomer(int cust_id) throws SQLException {
        String sql = "select * from user where id = " + cust_id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String password = resultSet.getString(3);
            System.out.println(id+" " + name +" " + password);
        } else {
            System.out.println("no record found");
        }
    }
        @Override
        public boolean getEmployeeLogin(String user, String password) throws SQLException {
            String sql = "select * from employee where user_name = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login Successful");
                return true;
            } else {
                System.out.println("That username, password combination is incorrect");
                return false;

            }
}


}



