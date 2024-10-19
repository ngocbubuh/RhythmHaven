package com.sap.rhythmhaven.entity;

import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

public class UserEntity {
    private String username;
    private String password;

    public UserEntity(EditText loginEmail, EditText loginPassword) {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
