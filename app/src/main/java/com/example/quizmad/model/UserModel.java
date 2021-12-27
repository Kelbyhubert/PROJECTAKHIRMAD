package com.example.quizmad.model;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String userUID;
    private String username;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private String userRole;

    // pake ini saat register
    public UserModel(String userUID, String username, String userEmail, String userPassword, String userGender, String userRole) {
        this.userUID = userUID;
        this.username = username;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userGender = userGender;
        this.userRole = userRole;
    }


    // pake ini saat ingin memasukan data user ke arraylist atau lain lain
    public UserModel(String userUID, String username, String userRole) {
        this.userUID = userUID;
        this.username = username;
        this.userRole = userRole;
    }



    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userUID='" + userUID + '\'' +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
