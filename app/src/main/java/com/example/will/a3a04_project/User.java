package com.example.will.a3a04_project;

/**
 * Created by will on 2017-04-03.
 */

public class User {
    public String Name;
    public String Password;
    public String Email;
    public int UserType;

    public User(){
        this.Name = "Null";
        this.Password = "Null";
        this.Email = "Null";
        this.UserType = 0;
    }
    public User(String name, String password, String email, int UserType){
        this.Name = name;
        this.Password = password;
        this.Email = email;
        this.UserType = UserType;
    }


    private String getName(){
        return this.Name;
    }
    private String getPassword(){
        return this.Password;
    }
    private String getEmail(){
        return this.Email;
    }
    public void SetName(String Name){
        this.Name = Name;
    }
    public void SetPassword(String Password){
        this.Password = Password;
    }
    public void SetEmail(String Email){
        this.Email = Email;
    }
    private int getUserType(){ return this.UserType;}
    public void SetUserType(int usertype){ this.UserType = usertype;}


}
