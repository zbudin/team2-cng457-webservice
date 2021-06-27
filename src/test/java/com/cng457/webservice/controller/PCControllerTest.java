package com.cng457.webservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.cng457.webservice.WebServiceApplication;
import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.Product;
import com.cng457.webservice.service.PCService;

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
public class PCControllerTest {
    @InjectMocks
    PCController pcController;

    @Mock
    PCService pcService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void getComputers() {
        PC pc1 = new PC("Alienware", "X17", "24", 10000.0, "AMD Ryzen 9 5800M", 64, "3840 x 2160", 1024);
        PC pc2 = new PC("Asus", "ROG", "27", 5000.0, "AMD Ryzen 9 5950x", 32, "3840 x 2160", 1024);
        PC pc3 = new PC("Asus", "ROG", "34", 9000.0, "AMD Ryzen 9 5950x", 64, "3840 x 2160", 2048);
        PC pc4 = new PC("MSI", "MEG", "27", 6000.0, "AMD Ryzen 9 5900x", 64, "2560 x 1440", 1024);
        PC pc5 = new PC("Gigabyte", "Aorus", "24", 4000.0, "AMD Ryzen 7 5800x", 64, "1920 x 1080", 512);
        
        List<PC> computers = new ArrayList<PC>();
        computers.add(pc1);
        computers.add(pc2);
        computers.add(pc3);
        computers.add(pc4);
        computers.add(pc5);

        when(pcController.all()).thenReturn(new ArrayList<PC>(computers));

        List<PC> computerList = pcController.all();

        assertEquals(computers.size(), computerList.size());
        assertEquals("Gigabyte", computerList.get(computerList.size() - 1).getBrand());
    }

    @Test
    void getComputers2() {
        assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/computers", Product[].class)[0].getBrand()
                .equals("Alienware"));
    }

}
