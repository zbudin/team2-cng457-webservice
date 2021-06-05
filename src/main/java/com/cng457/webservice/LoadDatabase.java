package com.cng457.webservice;

import com.cng457.webservice.entity.PC;
import com.cng457.webservice.entity.Phone;
import com.cng457.webservice.repository.IPCRepository;

import com.cng457.webservice.repository.IPhoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(IPCRepository repository, IPhoneRepository p_repository) {

        return args -> {
            log.info("Preloading " + repository
                    .save(new PC("Asus", "ROG", "27", 5000.0, "AMD Ryzen 9 5950x", 32, "3840 x 2160", 1024)));
            log.info("Preloading " + repository
                    .save(new PC("Asus", "ROG", "34", 9000.0, "AMD Ryzen 9 5950x", 64, "3840 x 2160", 2048)));
            log.info("Preloading " + repository
                    .save(new PC("MSI", "MEG", "27", 6000.0, "AMD Ryzen 9 5900x", 64, "2560 x 1440", 1024)));
            log.info("Preloading " + repository
                    .save(new PC("Gigabyte", "Aorus", "24", 4000.0, "AMD Ryzen 7 5800x", 64, "1920 x 1080", 512)));


            log.info("Preloading " + p_repository
                    .save(new Phone("Samsung", "Galaxy Note 10", "7", 950.0,8 )));
            log.info("Preloading " + p_repository
                    .save(new Phone("Apple", "IPhone X", "6", 1000.0,6 )));
            log.info("Preloading " + p_repository
                    .save(new Phone("Nokia", "8 Plus", "4", 500.0,4 )));
            log.info("Preloading " + p_repository
                    .save(new Phone("Xiaomi", "Mi 9T", "4", 300.0,6 )));

        };
    }
}