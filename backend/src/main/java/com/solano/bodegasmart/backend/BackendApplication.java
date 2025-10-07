package com.solano.bodegasmart.backend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BackendApplication {

    @PostConstruct
    public void init() {
        // Establecer la zona horaria de Perú para toda la aplicación
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
