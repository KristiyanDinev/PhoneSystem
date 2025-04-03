package me.kristiyandinev.PhoneSystem.models;

public class LoginUserModel {

    public String email;

    public String password;

    public LoginUserModel() {
        password = "";
        email = "";
    }

    public LoginUserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
