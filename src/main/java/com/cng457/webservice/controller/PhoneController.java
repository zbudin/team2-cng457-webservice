package com.cng457.webservice.controller;

import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {
    @Autowired
    private PhoneService service;

    @GetMapping("/phones")
    public List<Phone> findAllPhones(){
        return service.getPhones();
    }

    @GetMapping("/phone/{id}")
    public Phone findPhoneById(@PathVariable long id){
        return service.getPhone(id);
    }

    @PostMapping("/addPhone")
    public Phone addPhone(@RequestBody Phone p){
        return service.savePhone(p);
    }
    @PostMapping("/addPhones")
    public List<Phone> addPhones(@RequestBody List<Phone> p){
        return service.savePhones(p);
    }
}
