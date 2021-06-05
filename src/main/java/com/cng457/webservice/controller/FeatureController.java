package com.cng457.webservice.controller;

import com.cng457.webservice.entity.Feature;
import com.cng457.webservice.entity.ItemNotFoundException;
import com.cng457.webservice.repository.IFeatureRepository;
import com.cng457.webservice.repository.IPCRepository;
import com.cng457.webservice.repository.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeatureController {

    @Autowired
    private final IFeatureRepository repository;

    @Autowired
    private final IPCRepository pcRepository;

    @Autowired
    private final IPhoneRepository phoneRepository;

    FeatureController(IFeatureRepository repository, IPCRepository pcRepository, IPhoneRepository phoneRepository) {
        this.repository = repository;
        this.pcRepository = pcRepository;
        this.phoneRepository = phoneRepository;
    }

    @GetMapping("/features")
    List<Feature> all() {
        return repository.findAll();
    }

    @GetMapping("/features/{id}")
    Feature one(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping("/computers/{productId}/features")
    List<Feature> findFeaturesById(@PathVariable Long productId) {
        return repository.findByProductId(productId);
    }

    @PostMapping("/computers/{productId}/features/add")
    Feature addFeature(@PathVariable Long productId, @RequestBody Feature feature) {
        return pcRepository.findById(productId).map(product -> {
            feature.setProduct(product);
            return repository.save(feature);
        }).orElseThrow(() -> new ItemNotFoundException(productId));
    }

    @GetMapping("/phones/{productId}/features")
    List<Feature> findPhoneFeaturesById(@PathVariable Long productId) {
        return repository.findByProductId(productId);
    }

    @PostMapping("/phones/{productId}/features/add")
    Feature addPhoneFeature(@PathVariable Long productId, @RequestBody Feature feature) {
        return phoneRepository.findById(productId).map(product -> {
            feature.setProduct(product);
            return repository.save(feature);
        }).orElseThrow(() -> new ItemNotFoundException(productId));
    }
}
