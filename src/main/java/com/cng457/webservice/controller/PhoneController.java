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

    /**
     * Creates a new phone to be used later.
     * @param phone
     * @return Phone object
     */
    @PostMapping("/phones/create")
    Phone createPhone(@RequestBody Phone phone) {
        return repository.save(phone);
    }

    /**
     * Creates multiple phones to be used later.
     * @param phones
     * @return list of Phone objects
     */
    @PostMapping("/phones/createMultiple")
    List<Phone> createPhones(@RequestBody List<Phone> phones) {
        return repository.saveAll(phones);
    }

    /**
     * Retrieves and returns a list of available phones in the system.
     * @return list of Phone objects
     */
    @GetMapping("/phones")
    List<Phone> all() {
        return service.getPhones();
    }

    /**
     * Retrieves and returns a phone with given id.
     * @param id
     * @return Phone object
     */
    @GetMapping("/phones/{id}")
    Phone getById(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    /**
     * Retrieves and returns a list of phones with given brand.
     * @param brand
     * @return List of Phone objects
     */
    @GetMapping("/phones/brand/{brand}")
    List<Phone> findByBrand(@PathVariable String brand) {

        return repository.findByBrand(brand);
    }

    /**
     * Retrieves and returns a list of phones with given model.
     * @param model
     * @return List of Phone objects.
     */
    @GetMapping("/phones/model/{model}")
    List<Phone> findByModel(@PathVariable String model) {

        return repository.findByModel(model);
    }

    /**
     * Retrieves and returns a list of phones with given screensize.
     * @param screenSize
     * @return List of Phone objects.
     */
    @GetMapping("/phones/screenSize/{screenSize}")
    List<Phone> findByScreenSize(@PathVariable String screenSize) {
        return repository.findByScreenSize(screenSize);
    }

    /**
     * Retrieves and returns list of phones with given price
     * @param price
     * @return List of Phone objects.
     */
    @GetMapping("/phones/price/{price}")
    List<Phone> findByPrice(@PathVariable Double price) {
        return repository.findByPrice(price);
    }

    /**
     * Retrieves and returns a list of phones with given memory.
     * @param memory
     * @return List of Phone objects.
     */
    @GetMapping("/phones/internalMemory/{memory}")
    List<Phone> findByMemory(@PathVariable int memory) {

        return repository.findByInternalMemory(memory);
    }

    /**
     * Retrieves and returns a list of phones with given parameters.
     * @param brand
     * @param model
     * @param screenSize
     * @param minScreenSize
     * @param maxScreenSize
     * @param minPrice
     * @param maxPrice
     * @param internalMemory
     * @param feature
     * @return List of Phone objects.
     */
    @GetMapping("/phones/search")
    List<Phone> findByCriteria(@RequestParam(required = false) String brand, @RequestParam(required = false) String model,
                            @RequestParam(required = false) String screenSize,@RequestParam(required = false) String minScreenSize,
                               @RequestParam(required = false) String maxScreenSize,  @RequestParam(required = false) String minPrice,
                            @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String internalMemory,
                                @RequestParam(required = false) String feature) {
        return service.findPhonesByCriteria(brand, model, screenSize,minScreenSize,maxScreenSize, minPrice, maxPrice, internalMemory,feature);
    }

    /**
     * Retrieves and returns list of phones with given feature.
     * @param feature
     * @return List of Phone objects.
     */
    @GetMapping("/phones/byFeature")
    List<Phone> findByQuery(@RequestParam(required = false) String feature) {
        return repository.queryBy(feature);
    }
}
