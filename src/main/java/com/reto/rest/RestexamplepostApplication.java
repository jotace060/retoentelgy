package com.reto.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class RestexamplepostApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestexamplepostApplication.class, args);
    }

    @Bean
    public RestTemplate resttemplate() {
            return new RestTemplate();
    }


}
