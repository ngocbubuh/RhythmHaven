package com.sap.rhythmhaven.entity;

import android.widget.EditText;

public class UserEntity {
    private String  login_email;
    private String  login_password;

    public UserEntity(EditText loginEmail, EditText loginPassword) {
    }

    public UserEntity(String email, String password) {
    }

    public String getLogin_email() {
        return login_email;
    }

    public void setLogin_email(String login_email) {
        this.login_email = login_email;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }
}
