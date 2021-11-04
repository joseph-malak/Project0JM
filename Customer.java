package com.demo2;

public class Customer {
    private int id;
    private String account;
    private String name;
    private String email;
    private String userName;
    private String password;
    private double balances;
    private static boolean login = false;
    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }



    public Customer(String one, String two){
        this.name = name;
        this.email = email;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Customer() {

    }

    public Customer(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public void getId(Customer customer){}
    public void setId(int id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public int getId() {
        return id;
    }
}
