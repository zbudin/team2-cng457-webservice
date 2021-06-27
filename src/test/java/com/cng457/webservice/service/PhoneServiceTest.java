package com.cng457.webservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.repository.IPCRepository;

import com.cng457.webservice.repository.IPhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PhoneServiceTest {
    @InjectMocks
    PhoneService phoneService;

    @Mock
    IPhoneRepository phoneRepository;

    @Test
    void getPhones() {
        Phone phone1 = new Phone("Nokia","Sirocco 8","5",3000.0,128);
        Phone phone2 = new Phone("Apple", "IPhone X", "6", 10000.0, 256);
        Phone phone3 = new Phone("Samsung", "Galaxy Note 10", "7", 9500.0, 256);

        List<Phone> phones = new ArrayList<Phone>();
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);

        when(phoneRepository.findAll()).thenReturn(new ArrayList<Phone>(phones));

        List<Phone> phoneslist = phoneService.getPhones();
        assertEquals(phones.size(), phoneslist.size());
        assertEquals("5", phoneslist.get(0).getScreenSize());
        assertEquals("IPhone X", phoneslist.get(1).getModel());

    }

}
