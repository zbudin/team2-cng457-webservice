package com.cng457.webservice.controller;

import java.util.List;

import com.cng457.webservice.entity.PC;

import com.cng457.webservice.repository.IPCRepository;
import com.cng457.webservice.service.PCService;
import com.cng457.webservice.entity.ItemNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PCController {

    @Autowired
    private final IPCRepository repository;

    @Autowired
    private final PCService pcService;

    PCController(IPCRepository repository, PCService pcService) {
        this.repository = repository;
        this.pcService = pcService;
    }

    
    /** 
     * Creates a new computer that could be listed later.
     * @param computer
     * @return PC
     */
    @PostMapping("/computers/create")
    PC createComputer(@RequestBody PC computer) {
        return repository.save(computer);
    }

    
    /** 
     * Returns all the computers that are registered in the database.
     * @return List<PC>
     */
    @GetMapping("/computers")
    List<PC> all() {
        return pcService.getComputers();
    }

    
    /** 
     * Returns selected computer that is registered in the database by it's identifier.
     * @param id
     * @return PC
     */
    @GetMapping("/computers/{id}")
    PC getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    
    /** 
     * Returns filtered computers by brand.
     * @param brand
     * @return List<PC>
     */
    @GetMapping("/computers/brand/{brand}")
    List<PC> findByBrand(@PathVariable String brand) {

        return repository.findByBrand(brand);
    }

    
    /** 
     * Returns filtered computers by model.
     * @param model
     * @return List<PC>
     */
    @GetMapping("/computers/model/{model}")
    List<PC> findByModel(@PathVariable String model) {

        return repository.findByModel(model);
    }

    
    /** 
     * Returns filtered computers by screen size.
     * @param screenSize
     * @return List<PC>
     */
    @GetMapping("/computers/screenSize/{screenSize}")
    List<PC> findByScreenSize(@PathVariable String screenSize) {

        return repository.findByScreenSize(screenSize);
    }

    
    /** 
     * Returns filtered computers by their price.
     * @param price
     * @return List<PC>
     */
    @GetMapping("/computers/price/{price}")
    List<PC> findByPrice(@PathVariable String price) {

        return repository.findByPrice(Double.valueOf(price));
    }

    
    /** 
     * Returns filtered computers by processor.
     * @param processor
     * @return List<PC>
     */
    @GetMapping("/computers/processor/{processor}")
    List<PC> findByProcessor(@PathVariable String processor) {

        return repository.findByProcessor(processor);
    }

    
    /** 
     * Returns filtered computers by memory.
     * @param memory
     * @return List<PC>
     */
    @GetMapping("/computers/memory/{memory}")
    List<PC> findByMemory(@PathVariable String memory) {

        return repository.findByMemory(Integer.valueOf(memory));
    }

    
    /** 
     * Returns filtered computers by screen resolution.
     * @param screenResolution
     * @return List<PC>
     */
    @GetMapping("/computers/screenResolution/{screenResolution}")
    List<PC> findByScreenResolution(@PathVariable String screenResolution) {

        return repository.findByScreenResolution(screenResolution);
    }

    
    /** 
     * Returns filtered computers by storage.
     * @param storage
     * @return List<PC>
     */
    @GetMapping("/computers/storage/{storage}")
    List<PC> findByStorage(@PathVariable String storage) {

        return repository.findByStorage(Integer.valueOf(storage));
    }

    
    /** 
     * Returns filtered computers by multiple filters. All of the filters are optional 
     * @param brand
     * @param model
     * @param screenSize
     * @param minPrice
     * @param maxPrice
     * @param processor
     * @param memory
     * @param screenResolution
     * @param storage
     * @param feature
     * @return List<PC>
     */
    @GetMapping("/computers/search")
    List<PC> findByCriteria(@RequestParam(required = false) String brand, @RequestParam(required = false) String model,
            @RequestParam(required = false) String screenSize, @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice, @RequestParam(required = false) String processor,
            @RequestParam(required = false) String memory, @RequestParam(required = false) String screenResolution,
            @RequestParam(required = false) String storage, @RequestParam(required = false) String feature) {
        return pcService.findComputersByCriteria(brand, model, screenSize, minPrice, maxPrice, processor, memory,
                screenResolution, storage, feature);
    }

    
    /** 
     * Returns the computers filtered by their received rating from comments.
     * @param rating
     * @return List<PC>
     */
    @GetMapping("/computers/byRating")
    List<PC> findByComment(@RequestParam(required = false) int rating) {

        return repository.findByRating(rating);
    }

}