package com.demo2;

public class Customer {
    private static int id;
    private static String name;
    private static String email;
    private static String userName;
    private static String password;
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
    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String toString() {
        return "Customer " +
                "ID =" + id +
                ", Name ='" + name + '\'' +
                ", Email ='" + email + '\'' +
                '}';
    }
}
