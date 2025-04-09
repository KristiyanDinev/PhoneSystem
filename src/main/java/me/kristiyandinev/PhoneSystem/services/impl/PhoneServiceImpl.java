package me.kristiyandinev.PhoneSystem.services.impl;

import jakarta.transaction.Transactional;
import me.kristiyandinev.PhoneSystem.database.entities.PhoneEntity;
import me.kristiyandinev.PhoneSystem.database.entities.UserEntity;
import me.kristiyandinev.PhoneSystem.database.repositories.IPhoneRepository;
import me.kristiyandinev.PhoneSystem.services.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PhoneServiceImpl implements IPhoneService {

    @Autowired
    private IPhoneRepository phoneRepository;


    @Override
    public PhoneEntity addPhone(String phone, UserEntity userEntity) {
        PhoneEntity phoneEntity = new PhoneEntity(phone, userEntity, null);
        return phoneRepository.save(phoneEntity);
    }

    @Override
    public void removePhone(String phone, UserEntity userEntity) {
        PhoneEntity phoneEntity = new PhoneEntity(phone, userEntity, null);
        phoneRepository.delete(phoneEntity);
    }
}
