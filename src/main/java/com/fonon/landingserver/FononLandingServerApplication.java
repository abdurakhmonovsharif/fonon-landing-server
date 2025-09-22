package com.fonon.landingserver;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Fonon Landing API", version = "1.0.0"))
public class FononLandingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FononLandingServerApplication.class, args);
    }
}
