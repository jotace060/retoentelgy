package com.reto.rest.service;
import com.reto.rest.RestexamplepostApplicationTests;
import com.reto.rest.controller.UserController;
import com.reto.rest.dto.User;
import com.reto.rest.dto.UserParse;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserService.class)
class UserServiceTestMockito {

    @Value("${endpoint}")
    String endpoint;

    @Mock
    private RestTemplate resTemplate;

    @InjectMocks
    private UserService userService;

    @Autowired
    private UserService userServiceReal;

    ResponseEntity<User> internalServer = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    ResponseEntity<User> badRequest = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    ResponseEntity<User> notFound = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    ResponseEntity<User> forbidden = new ResponseEntity<>(HttpStatus.FORBIDDEN);
    ResponseEntity<User> timeOut = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);

    @Test
    void transDataTest() {
        List<String> data = new ArrayList<String>();
        data.add("1|Bluth|george.bluth@reqres.in");
        data.add("2|Weaver|janet.weaver@reqres.in");
        data.add("3|Wong|emma.wong@reqres.in");
        data.add("4|Holt|eve.holt@reqres.in");
        data.add("5|Morris|charles.morris@reqres.in");
        data.add("6|Ramos|tracey.ramos@reqres.in");
        UserParse userParse = userServiceReal.transData();
        assertEquals(data, userParse.data);
    }

    @Test
    void getInternalServerError() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Mockito.when(resTemplate.exchange(endpoint, HttpMethod.GET, entity, User.class))
                .thenReturn(internalServer);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, userService.getRequest().getStatusCode());
    }

    @Test
    void getNotFound() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Mockito.when(resTemplate.exchange(endpoint, HttpMethod.GET, entity, User.class))
                .thenReturn(notFound);
        Assert.assertEquals(HttpStatus.NOT_FOUND, userService.getRequest().getStatusCode());
    }

    @Test
    void getForbidden() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Mockito.when(resTemplate.exchange(endpoint, HttpMethod.GET, entity, User.class))
                .thenReturn(forbidden);
        Assert.assertEquals(HttpStatus.FORBIDDEN, userService.getRequest().getStatusCode());
    }

    @Test
    void getTimeOut() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Mockito.when(resTemplate.exchange(endpoint, HttpMethod.GET, entity, User.class))
                .thenReturn(timeOut);
        Assert.assertEquals(HttpStatus.REQUEST_TIMEOUT, userService.getRequest().getStatusCode());
    }

    @Test
    void getBadRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Mockito.when(resTemplate.exchange(endpoint, HttpMethod.GET, entity, User.class))
                .thenReturn(badRequest);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, userService.getRequest().getStatusCode());
    }

}




