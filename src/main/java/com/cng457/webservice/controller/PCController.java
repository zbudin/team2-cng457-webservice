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

    @PostMapping("/computers/create")
    PC createComputer(@RequestBody PC computer) {
        return repository.save(computer);
    }

    @PostMapping("/computers/createMultiple")
    List<PC> createComputers(@RequestBody List<PC> computers) {
        return repository.saveAll(computers);
    }

    @GetMapping("/computers")
    List<PC> all() {
        return repository.findAll();
    }

    @GetMapping("/computers/{id}")
    PC getById(@PathVariable Long id) {

        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping("/computers/brand/{brand}")
    List<PC> findByBrand(@PathVariable String brand) {

        return repository.findByBrand(brand);
    }

    @GetMapping("/computers/model/{model}")
    List<PC> findByModel(@PathVariable String model) {

        return repository.findByModel(model);
    }

    @GetMapping("/computers/screenSize/{screenSize}")
    List<PC> findByScreenSize(@PathVariable String screenSize) {

        return repository.findByScreenSize(screenSize);
    }

    @GetMapping("/computers/price/{price}")
    List<PC> findByPrice(@PathVariable String price) {

        return repository.findByPrice(Double.valueOf(price));
    }

    @GetMapping("/computers/processor/{processor}")
    List<PC> findByProcessor(@PathVariable String processor) {

        return repository.findByProcessor(processor);
    }

    @GetMapping("/computers/memory/{memory}")
    List<PC> findByMemory(@PathVariable String memory) {

        return repository.findByMemory(Integer.valueOf(memory));
    }

    @GetMapping("/computers/screenResolution/{screenResolution}")
    List<PC> findByScreenResolution(@PathVariable String screenResolution) {

        return repository.findByScreenResolution(screenResolution);
    }

    @GetMapping("/computers/storage/{storage}")
    List<PC> findByStorage(@PathVariable String storage) {

        return repository.findByStorage(Integer.valueOf(storage));
    }

    @GetMapping("/computers/search")
    List<PC> findByCriteria(@RequestParam(required = false) String brand, @RequestParam(required = false) String model,
            @RequestParam(required = false) String screenSize, @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice, @RequestParam(required = false) String processor,
            @RequestParam(required = false) String memory, @RequestParam(required = false) String screenResolution,
            @RequestParam(required = false) String storage) {
        return pcService.findComputersByCriteria(brand, model, screenSize, minPrice, maxPrice, processor, memory,
                screenResolution, storage);
    }

}