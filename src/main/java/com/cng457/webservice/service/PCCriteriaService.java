package com.cng457.webservice.service;

import java.util.List;

import com.cng457.webservice.CriteriaSpecification;
import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.SearchCriteria;
import com.cng457.webservice.entity.SearchOperation;
import com.cng457.webservice.repository.IPCCriteriaService;
import com.cng457.webservice.repository.IPCRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PCCriteriaService implements IPCCriteriaService {

    @Autowired
    private IPCRepository computerRepository;

    @Override
    public List<PC> findComputersByCriteria() {

        CriteriaSpecification<PC> genericSpesification = new CriteriaSpecification<PC>();
        genericSpesification.add(new SearchCriteria("brand", "Asus", SearchOperation.EQUAL));
        genericSpesification.add(new SearchCriteria("model", "ROG", SearchOperation.EQUAL));

        return computerRepository.findAll(genericSpesification);
    }
}