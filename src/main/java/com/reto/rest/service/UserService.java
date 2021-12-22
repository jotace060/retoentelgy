package com.reto.rest.service;

import com.reto.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Value("${endpoint}")
    String endpoint;

    /**
     * Metodo Http Spring
     */

    private RestTemplate restTemplate;

    /**
     * @param restTemplate
     */
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Método para transformar data
     * @return
     */
    public UserParse transData() {
        ResponseEntity<User> user = getRequest();
        List<Data> lista = user.getBody().data;
        UserParse userParse = new UserParse();
        List<String> listaAdd = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            String concatenado = lista.get(i).getId() + "|" + lista.get(i).getLast_name() + "|" + lista.get(i).getEmail();
            listaAdd.add(concatenado);
        }
        userParse.data = listaAdd;
        return userParse;
    }

    /**
     * Método para traer información del endpoint
     * @return
     */
    public ResponseEntity<User> getRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<User> user = restTemplate.exchange("https://reqres.in/api/users", HttpMethod.GET, entity, User.class);
        return user;
    }

}
