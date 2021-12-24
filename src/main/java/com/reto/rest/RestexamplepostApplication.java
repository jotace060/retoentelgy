package com.reto.rest;
import com.reto.rest.config.JobConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@Import(JobConfiguration.class)
public class RestexamplepostApplication {

    public static void main(String[] args) {
          SpringApplication.run(RestexamplepostApplication.class, args);
    }

    @Bean
    public RestTemplate resttemplate() {
            return new RestTemplate();
    }


}
