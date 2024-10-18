package com.sap.rhythmhaven.entity;

import com.google.gson.annotations.SerializedName;

public class UserEntity {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
