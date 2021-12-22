package com.reto.rest.service;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserService.class)
class UserServiceTestJUnit {

    @Autowired
    UserService userService;

    @Test
    public void getTransData(){
        List<String> data = new ArrayList<String>();
        data.add("1|Bluth|george.bluth@reqres.in");
        data.add("2|Weaver|janet.weaver@reqres.in");
        data.add("3|Wong|emma.wong@reqres.in");
        data.add("4|Holt|eve.holt@reqres.in");
        data.add("5|Morris|charles.morris@reqres.in");
        data.add("6|Ramos|tracey.ramos@reqres.in");;
        assertEquals(userService.transData().data,data);
    }

    @Test
    public void getRequest(){
        assertEquals(userService.getRequest().getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void getNotEqual(){
        assertNotEquals(userService.getRequest().getStatusCode(),HttpStatus.REQUEST_TIMEOUT);
    }

}