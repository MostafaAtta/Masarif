package com.atta.masarif.model;

public class User {

    private int id;
    private String username, password;


    public User(String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }



    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return username;
    }

    public String getPassword(){
        return password;
    }

}
