package com.demo2;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        boolean loginc = false;
        CustomerDao daoc = CustomerDaoFactory.getCustomerDao();
        EmployeeDao dao = EmployeeDaoFactory.getEmployeeDao();
        int inputint = 0;
        int inputint2;
        int inputint3;
        int loopint = 0;
        int winput = 0;
        boolean loginEm;
        double inputD;
        String inputS1;
        String inputS2;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bank of WGF");
        while (winput != 4) {
            System.out.println("If you are a returning customer 1:\n" +
                    "If you are a new user... please enter 2 to get Started!\n" +
                    "If you are an employee enter 3:\n" +
                    "If you would like to exit enter 4:");
            winput = scanner.nextInt();
            int a = winput;
            if (a == 1) {
                scanner.nextLine();
                System.out.println("Please enter your UserName:");
                inputS1 = scanner.nextLine();
                System.out.println("Please enter your Password:");
                inputS2 = scanner.nextLine();

                loginc = daoc.getCustomerAccount(inputS1, inputS2);
                loopint = 0;
                if (loginc == true) {
                    while (loopint != 7) {
                        System.out.println("If you would like to open a new account then enter 1:\n" +
                                "To view the balance of your accounts then enter 2:\n" +
                                "To withdraw money enter 3:\n" +
                                "To deposit money into your account enter 4:\n" +
                                "To make a transfer enter 5:\n" +
                                "To receive any incoming transfers enter 6:\n" +
                                "To exit enter 7:");
                        inputint = scanner.nextInt();
                        switch (inputint) {
                            case 1:
                                scanner.nextLine();
                                System.out.println("Please enter an account passcode for your new account:");
                                inputint = scanner.nextInt();
                                System.out.println("Please enter a balance for your account");
                                inputD = scanner.nextDouble();
                                System.out.println("Please enter your personal account ID");
                                inputint3 = scanner.nextInt();
                                daoc.NewBankAccount(inputint, inputD, inputint3);
                                break;

                            case 2:
                                System.out.println("Please enter the accounts code:");
                                inputint = scanner.nextInt();
                                System.out.println("Enter your personal account ID:");
                                inputint2 = scanner.nextInt();
                                daoc.viewAccount(inputint,inputint2);
                                break;

                            case 3:
                                System.out.println("Enter the account code of the account you would like to pull money from:");
                                inputint = scanner.nextInt();
                                System.out.println("Please enter the amount that you would like to withdraw:");
                                inputD = scanner.nextDouble();
                                System.out.println("Please enter your personal account ID");
                                inputint3 = scanner.nextInt();
                                if (inputint > 0 && inputD > 0)
                                    daoc.withdraw(inputint, inputD,inputint3);
                                else System.out.println("Cannot withdraw negative money");
                                break;

                            case 4:
                                System.out.println("Enter the account code of the account you would like to deposit money to:");
                                inputint = scanner.nextInt();
                                System.out.println("Please enter the amount that you would like to deposit:");
                                inputD = scanner.nextDouble();
                                System.out.println("Please enter your personal account ID");
                                inputint3 = scanner.nextInt();
                                if (inputint > 0 && inputD > 0)
                                    daoc.deposit(inputint, inputD,inputint3);
                                else System.out.println("Cannot deposit negative money");
                                break;

                            case 5:
                                System.out.println("Please enter the account code of the account you would like to pull from:");
                                inputint = scanner.nextInt();
                                System.out.println("Please enter the amount to transfer:");
                                inputD = scanner.nextInt();
                                System.out.println("Please enter the account code of the account you would like to deposit to:");
                                inputint2 = scanner.nextInt();
                                System.out.println("Please enter your personal account ID");
                                inputint3 = scanner.nextInt();
                                daoc.transferOut(inputint, inputD, inputint2, inputint3);
                                break;

                            case 6:
                                System.out.println("Please enter the transfer ID number:");
                                inputint = scanner.nextInt();
                                System.out.println("Please enter your personal account ID");
                                inputint2 = scanner.nextInt();
                                daoc.transferAccept(inputint,inputint2);
                                break;

                            case 7:
                                System.out.println("Exiting");
                                loginc = false;
                                loopint = 7;
                                break;
                        }
                    }
                } else {
                    System.out.println("an error occurred");
                }

            } else if (a == 2) {
                scanner.nextLine();
                System.out.println("To start creating an account we will need you to create a username and password:");
                System.out.println("Please enter a UserName:");
                inputS1 = scanner.nextLine();
                System.out.println("Please enter a Password:");
                inputS2 = scanner.nextLine();
                System.out.println("Please enter an account ID");
                inputint = scanner.nextInt();
                daoc.NewAccount(inputint,inputS1, inputS2);
            }
            else if (a == 3) {
                scanner.nextLine();
                System.out.println("Please enter your UserName:");
                inputS1 = scanner.nextLine();
                System.out.println("Please enter your Password:");
                inputS2 = scanner.nextLine();
                loginEm = dao.getEmployeeLogin(inputS1, inputS2);

                if (loginEm == true) {
                    int b =0 ;

                    while (b != 4) {

                        System.out.println("If you would like to activate an account enter 1:\n" +
                                "To Deactivate an account enter 2:\n" +
                                "View a customer enter 3:\n" +
                                "To exit enter 4:\n");
                        inputint = scanner.nextInt();
                        switch (inputint) {
                            case 1:
                                System.out.println("Please enter the account code of the account you would like to activate:");
                                inputint = scanner.nextInt();
                                dao.Activate(inputint);
                                break;

                            case 2:
                                System.out.println("Please enter the account code of the account you would like to Deactivate:");
                                inputint = scanner.nextInt();
                                dao.Deactivate(inputint);
                                break;

                            case 3:
                                System.out.println("Please enter the account code of the customer you would like to view");
                                inputint = scanner.nextInt();
                                System.out.println("Pulling from list....");
                                dao.IdCusotomer(inputint);



                                break;

                            case 4:
                                loginEm = false;
                                b=4;
                                inputint =4;
                                break;
                        }
                    }
                } else {

                }
            }
            }
        System.out.println("Please come again!");
        }
    }

