package com.cng457.webservice.controller;

import com.cng457.webservice.entity.ItemNotFoundException;
import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.repository.IPCRepository;
import com.cng457.webservice.repository.IPhoneRepository;
import com.cng457.webservice.service.PCService;
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

    @GetMapping("/phones/{id}/details")
    Phone one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping("/phones/{brand}")
    List<Phone> findByBrand(@PathVariable String brand) {

        return repository.findByBrand(brand);
    }

    @GetMapping("/phones/{model}")
    List<Phone> findByModel(@PathVariable String model) {

        return repository.findByModel(model);
    }

    @GetMapping("/phones/{screenSize}")
    List<Phone> findByScreenSize(@PathVariable String screenSize) {
        return repository.findByScreenSize(screenSize);
    }

    @GetMapping("/phones/{price}")
    List<Phone> findByPrice(@PathVariable Double price) {
        return repository.findByPrice(price);
    }

    @GetMapping("/phones/{memory}")
    List<Phone> findByMemory(@PathVariable int memory) {

        return repository.findByInternalMemory(memory);
    }

    @GetMapping("/phones/byCriteria")
    List<Phone> findByCriteria(@RequestParam(required = false) String brand, @RequestParam(required = false) String model,
                            @RequestParam(required = false) String screenSize,@RequestParam(required = false) String minScreenSize,
                               @RequestParam(required = false) String maxScreenSize,  @RequestParam(required = false) Double minPrice,
                            @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) int internalMemory) {
        return service.findPhonesByCriteria(brand, model, screenSize,minScreenSize,maxScreenSize, minPrice, maxPrice, internalMemory);
    }

    /*
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

     */
}
