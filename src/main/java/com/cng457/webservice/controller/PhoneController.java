package com.cng457.webservice.controller;

import com.cng457.webservice.entity.ItemNotFoundException;
import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.repository.IPhoneRepository;
import com.cng457.webservice.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoneController {
    @Autowired
    private final PhoneService service;

    @Autowired
    private final IPhoneRepository repository;

    PhoneController(IPhoneRepository repository, PhoneService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/phones/create")
    Phone createComputer(@RequestBody Phone phone) {
        return repository.save(phone);
    }

    @PostMapping("/phones/createMultiple")
    List<Phone> createComputers(@RequestBody List<Phone> phones) {
        return repository.saveAll(phones);
    }

    @GetMapping("/phones")
    List<Phone> all() {
        return repository.findAll();
    }

    @GetMapping("/phones/{id}")
    Phone getById(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }


    @GetMapping("/phones/brand/{brand}")
    List<Phone> findByBrand(@PathVariable String brand) {

        return repository.findByBrand(brand);
    }

    @GetMapping("/phones/model/{model}")
    List<Phone> findByModel(@PathVariable String model) {

        return repository.findByModel(model);
    }

    @GetMapping("/phones/screenSize/{screenSize}")
    List<Phone> findByScreenSize(@PathVariable String screenSize) {
        return repository.findByScreenSize(screenSize);
    }

    @GetMapping("/phones/price/{price}")
    List<Phone> findByPrice(@PathVariable Double price) {
        return repository.findByPrice(price);
    }

    @GetMapping("/phones/internalMemory/{memory}")
    List<Phone> findByMemory(@PathVariable int memory) {

        return repository.findByInternalMemory(memory);
    }

    @GetMapping("/phones/search")
    List<Phone> findByCriteria(@RequestParam(required = false) String brand, @RequestParam(required = false) String model,
                            @RequestParam(required = false) String screenSize,@RequestParam(required = false) String minScreenSize,
                               @RequestParam(required = false) String maxScreenSize,  @RequestParam(required = false) String minPrice,
                            @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String internalMemory,
                                @RequestParam(required = false) String feature) {
        return service.findPhonesByCriteria(brand, model, screenSize,minScreenSize,maxScreenSize, minPrice, maxPrice, internalMemory,feature);
    }

    @GetMapping("/phones/byFeature")
    List<Phone> findByQuery(@RequestParam(required = false) String feature) {
        return repository.queryBy(feature);
    }

}
