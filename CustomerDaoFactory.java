package com.demo2;

public class CustomerDaoFactory {

    private static CustomerDao daoc;

    private CustomerDaoFactory(){

    }

    public static CustomerDao getCustomerDao(){
        if(daoc ==null) {
            daoc = new CustomerDaoImlp();
        }
        return daoc;
    }
}
