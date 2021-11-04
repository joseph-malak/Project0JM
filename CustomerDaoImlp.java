package com.demo2;

import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class CustomerDaoImlp implements CustomerDao {
    Connection connection;
    public double balance;
    Scanner scanner = new Scanner(System.in);

    Random rand = new Random();
    public int randomValue = (int) Math.floor(Math.random() * (100000));
    Customer customer = new Customer();

    public CustomerDaoImlp() {
        this.connection = ConnectionFactory.getConnection();
    }


    @Override
    public boolean getCustomerAccount(String userName, String password) throws SQLException {
        String sql = "select * from user where user_name = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
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

    public void NewAccount(int numbers, String one, String two) throws SQLException {
        String sql = "insert into user values (?,?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, numbers);
        preparedStatement.setString(2, one);
        preparedStatement.setString(3, two);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Customer saved!");
        else
            System.out.println("Oops! something went wrong");
    }

    public void NewBankAccount(int numbers, Double one, int passcode) throws SQLException {
        String sql = "insert into accounts values (?,?, ?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, numbers);
        preparedStatement.setDouble(2, one);
        preparedStatement.setString(3, "false");
        preparedStatement.setInt(4, passcode);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Account saved!");
        else
            System.out.println("Oops! something went wrong");
    }


    @Override
    public void viewAccount(int accountId, int personalId) throws SQLException {
        String sql = "select balance from accounts where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, accountId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        } else {
            System.out.println("A problem has occurred");
        }
    }

    @Override
    public void deposit(int account_id, double transaction, int personalNumber) throws SQLException {
        String sql = "select balance from accounts where id = ? and account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, personalNumber);
        preparedStatement.setInt(2, account_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            balance = resultSet.getInt(1);
            if (balance > 0) {
                if (balance > 0) {
                    String sql2 = "select activated from accounts where activated = 'true' and account_id = ?";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                    preparedStatement2.setInt(1, account_id);
                    ResultSet resultSet3 = preparedStatement2.executeQuery();
                    if (resultSet3.next()) {
                        balance += transaction;
                        String sql4 = "Update accounts set balance = ? where account_id = ? and Id = ?";
                        PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
                        preparedStatement4.setDouble(1, balance);
                        preparedStatement4.setInt(2, account_id);
                        preparedStatement4.setInt(3, personalNumber);
                        int count = preparedStatement4.executeUpdate();
                        if (count > 0) {
                            System.out.println("Transaction Complete");
                        } else {
                            System.out.println("An error has occurred");
                        }
                    }else System.out.println("You account has not been activated");
                } else {
                    System.out.println("You account has not been activated");
                }
            } else System.out.println("Insufficient funds");
        } else System.out.println("Could not find your account");
    }


    @Override
    public void withdraw(int account_Id, double lessMoney, int personalId) throws SQLException {
        String sql = "select balance from accounts where id = ? and account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, personalId);
        preparedStatement.setInt(2, account_Id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            balance = resultSet.getInt(1);
            if (balance > 0) {
                String sql2 = "select activated from accounts where activated = 'true' and account_id = ?";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setInt(1, account_Id);
                ResultSet resultSet3 = preparedStatement2.executeQuery();
                if (resultSet3.next()) {
                    balance -= lessMoney;
                    if (balance > 0) {

                        String sql3 = "Update accounts set balance = ? where account_id = ? and Id = ?";
                        PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
                        preparedStatement3.setDouble(1, balance);
                        preparedStatement3.setInt(2, account_Id);
                        preparedStatement3.setInt(3, personalId);
                        int count = preparedStatement3.executeUpdate();
                        if (count > 0) {
                            System.out.println("Transaction Complete");
                        } else {
                            System.out.println("an error has occurred");
                        }
                    } else {
                        System.out.println("An error has occurred");
                    }

                } else System.out.println("Your account is not activated");
        }
        }
        }




    @Override
    public void transferOut(int account_id, double amount,int otherAccount, int personalId) throws SQLException {

        String sql1 = "insert into transactions values (?,?,?,?,?)";
        PreparedStatement preparedStatement4 = connection.prepareStatement(sql1);
        preparedStatement4.setInt(1, account_id);
        preparedStatement4.setDouble(2, amount);
        preparedStatement4.setString(3, "true");
        preparedStatement4.setDouble(4, randomValue);
        preparedStatement4.setInt(5, otherAccount);
        int count2 = preparedStatement4.executeUpdate();
        if (count2 > 0) {
            System.out.println("Your transaction ID is :" + randomValue);
            withdraw(account_id, amount,personalId);
            System.out.println("Transaction complete:");

        } else {
            System.out.println("an error has occurred");
        }

    }


    public void transferAccept(int transfer_id, int personalID) throws SQLException {
        int account_id;
        String sql6 = "Update transactions set pending = 'false' where transaction_id = " + transfer_id;
        PreparedStatement preparedStatement6 = connection.prepareStatement(sql6);
        int count = preparedStatement6.executeUpdate();
        if (count >0) {
            String sql = "select account_transfered from transactions where transaction_id = " + transfer_id;
            PreparedStatement preparedStatement5 = connection.prepareStatement(sql);
            ResultSet resultSet2 = preparedStatement5.executeQuery();
            if (resultSet2.next()) {
                    account_id = resultSet2.getInt(1);
                    String sql3 = "select amount from transactions where transaction_id = " + transfer_id;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql3);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        balance = resultSet.getInt(1);
                        deposit(account_id, balance, personalID);
                    } else {
                        System.out.println("An problem has occurred");
                    }
                } else {
                    System.out.println("an error has occurred");
                }
            }
        }
    }

