package com.demo2;

import java.sql.SQLException;

public interface CustomerDao {
    boolean getCustomerAccount(String userName, String password) throws SQLException;

    void viewAccount(int accountid, int personalId) throws SQLException;

    void withdraw(int id, double transaction, int personalAccount) throws SQLException;

    void deposit(int id, double transaction, int personalAccount) throws SQLException;

    void transferOut(int account_id, double amount,int otherAccount, int personalId) throws SQLException;

    void transferAccept(int transfer_id,int personalID) throws SQLException;

    void NewAccount(int numbers, String one, String two)throws SQLException;

    void NewBankAccount(int numbers, Double one, int passcode ) throws SQLException;
}
