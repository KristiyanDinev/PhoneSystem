package me.kristiyandinev.PhoneSystem.domain;

public class User {
    public Integer id;
    public String name;
    public String email;
    public String password;

    public User() {
        id = null;
        name = "";
        email = "";
        password = "";
    }

    public User(Integer _id, String _name, String _email, String _password) {
        id = _id;
        name = _name;
        email = _email;
        password = _password;
    }
}
