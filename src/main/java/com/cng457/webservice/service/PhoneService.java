package com.cng457.webservice.service;

import com.cng457.webservice.CriteriaSpecification;
import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.entity.SearchCriteria;
import com.cng457.webservice.entity.SearchOperation;
import com.cng457.webservice.repository.IPCRepository;
import com.cng457.webservice.repository.IPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private IPhoneRepository phoneRepository;

    public List<Phone> findPhonesByCriteria(String brand, String model, String screenSize, String minScreenSize,String maxScreenSize, Double minPrice,
                                               Double maxPrice, int internalMemory) {

        CriteriaSpecification<Phone> specification = new CriteriaSpecification<Phone>();
        if (brand != null)
            specification.add(new SearchCriteria("brand", brand, SearchOperation.EQUAL));
        if (model != null)
            specification.add(new SearchCriteria("model", model, SearchOperation.EQUAL));
        if (screenSize != null)
            specification.add(new SearchCriteria("screenSize", screenSize, SearchOperation.EQUAL));
        if  (minScreenSize != null)
            specification.add(new SearchCriteria("screenSize", screenSize, SearchOperation.GREATER_THAN_EQUAL));
        if  (maxScreenSize != null)
            specification.add(new SearchCriteria("screenSize", screenSize, SearchOperation.LESS_THAN_EQUAL));
        if (minPrice != null)
            specification.add(new SearchCriteria("price", minPrice, SearchOperation.GREATER_THAN_EQUAL));
        if (maxPrice != null)
            specification.add(new SearchCriteria("price", maxPrice, SearchOperation.LESS_THAN_EQUAL));
        if (internalMemory != 0)
            specification.add(new SearchCriteria("internalMemory", internalMemory, SearchOperation.EQUAL));

        return phoneRepository.findAll(specification);
    }
    /*
    @Autowired
    private IPhoneRepository phoneRepository;

    public Phone savePhone(Phone p){
        return phoneRepository.save(p);
    }
    public List<Phone> savePhones(List<Phone> phones){
        return phoneRepository.saveAll(phones);
    }

    public List<Phone> getPhones(){
        return phoneRepository.findAll();
    }
    public Phone getPhone(long id){
        return phoneRepository.findById(id).orElse(null);
    }

     */
}
