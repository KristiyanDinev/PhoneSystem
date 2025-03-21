package me.kristiyandinev.PhoneSystem.domain;


public class Phone {
    public String number;
    public Long user_id;
    public Long id;

    public Phone() {
        number = "";
        user_id = null;
        id = null;
    }

    public Phone(String _number, Long _user_id, Long _id) {
        number = _number;
        user_id = _user_id;
        id = _id;
    }
}
