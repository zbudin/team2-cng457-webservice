package com.cng457.webservice.service;

import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.repository.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    private IPhoneRepository repository;

    public Phone savePhone(Phone p){
        return repository.save(p);
    }
    public List<Phone> savePhones(List<Phone> phones){
        return repository.saveAll(phones);
    }

    public List<Phone> getPhones(){
        return repository.findAll();
    }
    public Phone getPhone(long id){
        return repository.findById(id).orElse(null);
    }
}
