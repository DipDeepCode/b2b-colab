package ru.ddc.b2bcolab;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.ddc.b2bcolab.upload.service.StorageService;

@SpringBootApplication
public class B2bColabApplication implements CommandLineRunner {

    @Resource
    private StorageService storageService;

    public static void main(String[] args) {
        SpringApplication.run(B2bColabApplication.class, args);
    }

    public void run(String[] args) {
        storageService.init();
    }
}
