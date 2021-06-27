package com.cng457.webservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import com.cng457.webservice.WebServiceApplication;
import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.entity.Product;
import com.cng457.webservice.service.PhoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = WebServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneControllerTest {
    @InjectMocks
    PhoneController phoneController;

    @Mock
    PhoneService phoneService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    void getPhones() {
        Phone phone1 = new Phone("Nokia","Sirocco 8","5",3000.0,128);
        Phone phone2 = new Phone("Apple", "IPhone X", "6", 10000.0, 256);
        Phone phone3 = new Phone("Samsung", "Galaxy Note 10", "7", 9500.0, 256);

        List<Phone> phones = new ArrayList<Phone>();
        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);

        when(phoneController.all()).thenReturn(new ArrayList<Phone>(phones));

        List<Phone> phoneList = phoneController.all();

        assertEquals(phones.size(), phoneList.size());
        assertEquals(256, phoneList.get(phoneList.size() - 1).getInternalMemory());
    }

    @Test
    void getPhones2() {
        assertTrue(this.testRestTemplate.getForObject("http://localhost:" + port + "/phones", Product[].class)[0].getBrand()
                .equals("Nokia"));
    }

}
