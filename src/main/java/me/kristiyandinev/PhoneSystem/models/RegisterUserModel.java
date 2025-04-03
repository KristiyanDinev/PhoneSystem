package me.kristiyandinev.PhoneSystem.models;

public class RegisterUserModel {

    public String name;

    public String email;

    public String password;

    public RegisterUserModel() {
        name = "";
        email = "";
        password = "";
    }

    public RegisterUserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
