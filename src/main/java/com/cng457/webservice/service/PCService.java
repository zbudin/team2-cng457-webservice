package com.cng457.webservice.service;

import java.util.List;

import com.cng457.webservice.CriteriaSpecification;
import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.SearchCriteria;
import com.cng457.webservice.entity.SearchOperation;
import com.cng457.webservice.repository.IPCService;
import com.cng457.webservice.repository.IPCRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PCService implements IPCService {

    @Autowired
    private IPCRepository computerRepository;

    @Override
    public List<PC> findComputersByCriteria(String brand, String model, String screenSize, String minPrice,
            String maxPrice, String processor, String memory, String screenResolution, String storage, String feature) {

        CriteriaSpecification<PC> specification = new CriteriaSpecification<PC>();
        if (brand != null)
            specification.add(new SearchCriteria("brand", brand, SearchOperation.EQUAL));
        if (model != null)
            specification.add(new SearchCriteria("model", model, SearchOperation.EQUAL));
        if (screenSize != null)
            specification.add(new SearchCriteria("screenSize", screenSize, SearchOperation.EQUAL));
        if (minPrice != null)
            specification
                    .add(new SearchCriteria("price", Double.valueOf(minPrice), SearchOperation.GREATER_THAN_EQUAL));
        if (maxPrice != null)
            specification.add(new SearchCriteria("price", Double.valueOf(maxPrice), SearchOperation.LESS_THAN_EQUAL));
        if (processor != null)
            specification.add(new SearchCriteria("processor", processor, SearchOperation.EQUAL));
        if (memory != null)
            specification.add(new SearchCriteria("memory", Integer.valueOf(memory), SearchOperation.EQUAL));
        if (screenResolution != null)
            specification.add(new SearchCriteria("screenResolution", screenResolution, SearchOperation.EQUAL));
        if (storage != null)
            specification.add(new SearchCriteria("storage", Integer.valueOf(storage), SearchOperation.EQUAL));
        if (feature != null)
            specification.add(new SearchCriteria("feature", feature, SearchOperation.EQUAL));

        return computerRepository.findAll(specification);
    }
}