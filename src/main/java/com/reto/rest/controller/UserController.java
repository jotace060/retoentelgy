package com.reto.rest.controller;

import com.reto.rest.alias.UserFinal;
import com.reto.rest.alias.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class UserController {
    private final RestTemplate restTemplate;

    @Value("${endpoint}")
    String endpoint;

    @Autowired
    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    //Post
    @PostMapping("/users/modify")
    public UserFinal modifyUser(@RequestBody Users request){
        Users response = request;
        String page = String.valueOf(response.page);
        String urlFinal = endpoint + "?page="+page;
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Users> user =restTemplate.exchange(urlFinal, HttpMethod.GET,entity,Users.class);
        UserFinal userFinal = new UserFinal();
        userFinal.data = user.getBody().data;
        return userFinal;
    }

    @GetMapping("/users")
    public ResponseEntity<Users> getUser(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Users> user =restTemplate.exchange(endpoint, HttpMethod.GET,entity,Users.class);

        return user;
    }


}