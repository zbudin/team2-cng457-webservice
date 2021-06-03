package com.cng457.webservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cng457.webservice.entity.Comment;
import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.Product;
import com.cng457.webservice.repository.IPCRepository;
import com.cng457.webservice.service.PCCriteriaService;
import com.cng457.webservice.entity.PCNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PCController {

    private final IPCRepository repository;

    @Autowired
    private final PCCriteriaService criteriaService;

    PCController(IPCRepository repository, PCCriteriaService criteriaService) {
        this.repository = repository;
        this.criteriaService = criteriaService;
    }

    @GetMapping("/computers")
    List<PC> all() {
        return repository.findAll();
    }

    @GetMapping("/computers/{productId}/details")
    PC one(@PathVariable Long productId) {

        return repository.findById(productId).orElseThrow(() -> new PCNotFoundException(productId));
    }

    @GetMapping("/computers/{productId}/comments")
    List<Comment> findCommentsById(@PathVariable Long productId) {
        Optional<PC> opt = repository.findById(productId);
        if (opt.isPresent()) {
            PC pc = opt.get();
            return pc.getComments();
        }
        return new ArrayList<Comment>();
    }

    @GetMapping("/computers/{brand}")
    List<PC> findByBrand(@PathVariable String brand) {

        return repository.findByBrand(brand);
    }

    @GetMapping("/computers/{model}")
    List<PC> findByModel(@PathVariable String model) {

        return repository.findByModel(model);
    }

    @GetMapping("/computers/{screenSize}")
    List<PC> findByScreenSize(@PathVariable String screenSize) {

        return repository.findByScreenSize(screenSize);
    }

    @GetMapping("/computers/{price}")
    List<PC> findByPrice(@PathVariable String price) {

        return repository.findByPrice(price);
    }

    @GetMapping("/computers/{processor}")
    List<PC> findByProcessor(@PathVariable String processor) {

        return repository.findByProcessor(processor);
    }

    @GetMapping("/computers/{memory}")
    List<PC> findByMemory(@PathVariable String memory) {

        return repository.findByMemory(memory);
    }

    @GetMapping("/computers/{screenResolution}")
    List<PC> findByScreenResolution(@PathVariable String screenResolution) {

        return repository.findByScreenResolution(screenResolution);
    }

    @GetMapping("/computers/{storage}")
    List<PC> findByStorage(@PathVariable String storage) {

        return repository.findByStorage(storage);
    }

    @PostMapping("/computers/create")
    PC createComputer(@RequestBody PC computer) {
        return repository.save(computer);
    }

    @GetMapping("/computers/byCriteria")
    List<PC> findByCriteria() {
        return criteriaService.findComputersByCriteria();
    }

}