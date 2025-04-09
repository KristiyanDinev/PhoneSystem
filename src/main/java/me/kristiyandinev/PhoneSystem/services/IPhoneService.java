package me.kristiyandinev.PhoneSystem.services;

import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;

public interface IPhoneService {
    PhoneEntity addPhone(String phone, UserEntity userEntity);
    void removePhone(String phone, UserEntity userEntity);
}
