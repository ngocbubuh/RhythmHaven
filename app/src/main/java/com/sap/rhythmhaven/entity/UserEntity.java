package com.sap.rhythmhaven.entity;

import android.widget.EditText;

public class UserEntity {
    private String username;
    private String password;

    public UserEntity(EditText loginEmail, EditText loginPassword) {
    }

    public UserEntity(String email, String password) {
        this.username = email;
        this.password = password;
    }

    public String getLogin_email() {
        return username;
    }

    public void setLogin_email(String login_email) {
        this.username = login_email;
    }

    public String getLogin_password() {
        return password;
    }

    public void setLogin_password(String login_password) {
        this.password = login_password;
    }
}
