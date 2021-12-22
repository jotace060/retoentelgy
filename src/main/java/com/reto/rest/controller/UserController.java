package com.reto.rest.controller;
import com.reto.rest.dto.User;
import com.reto.rest.dto.UserParse;
import com.reto.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Value("${endpoint}")
    String endpoint;

    @Autowired
    UserService userService;

    @PostMapping("/users/modify")
    public UserParse modifyUser(@RequestBody User user){
        UserParse userFinal= userService.transData();
        return userFinal;
    }


}