package com.reto.rest.service;
import com.reto.rest.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnit {

    @InjectMocks
    UserService userService;

    @Mock
    RestTemplate restTemplate;

    @Captor
    ArgumentCaptor<HttpEntity> entityCaptor;


    @Test
    public void getRequestTimeOut(){
        ResponseEntity<User> responseUser = new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        doReturn(responseUser).when(restTemplate).exchange(
                Mockito.eq("https://reqres.in/api/users"),
                Mockito.eq(HttpMethod.GET),
                any(HttpEntity.class),
                Mockito.eq(User.class));
        ResponseEntity<User> response = userService.getRequest();
        System.out.println(response.getStatusCode());
        assertEquals(response,responseUser);
        assertEquals(response.getBody(),responseUser.getBody());

    }

    @Test
    public void getForbidden(){
        ResponseEntity<User> responseUser = new ResponseEntity<>(HttpStatus.FORBIDDEN);
        doReturn(responseUser).when(restTemplate).exchange(
                Mockito.eq("https://reqres.in/api/users"),
                Mockito.eq(HttpMethod.GET),
                any(HttpEntity.class),
                Mockito.eq(User.class));
        ResponseEntity<User> response = userService.getRequest();
        System.out.println(response.getStatusCode());
        assertEquals(response,responseUser);
        assertEquals(response.getBody(),responseUser.getBody());

    }

    @Test
    public void getInternalServerError(){
        ResponseEntity<User> responseUser = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        doReturn(responseUser).when(restTemplate).exchange(
                Mockito.eq("https://reqres.in/api/users"),
                Mockito.eq(HttpMethod.GET),
                any(HttpEntity.class),
                Mockito.eq(User.class));
        ResponseEntity<User> response = userService.getRequest();
        System.out.println(response.getStatusCode());
        assertEquals(response,responseUser);
        assertEquals(response.getBody(),responseUser.getBody());

    }

    @Test
    public void getBadRequest(){
        ResponseEntity<User> responseUser = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        doReturn(responseUser).when(restTemplate).exchange(
                Mockito.eq("https://reqres.in/api/users"),
                Mockito.eq(HttpMethod.GET),
                any(HttpEntity.class),
                Mockito.eq(User.class));
        ResponseEntity<User> response = userService.getRequest();
        System.out.println(response.getStatusCode());
        assertEquals(response,responseUser);
        assertEquals(response.getBody(),responseUser.getBody());

    }


    @Test
    public void getUnauthorized(){
        HttpClientErrorException httpClientErrorException = new HttpClientErrorException(HttpStatus.UNAUTHORIZED,HttpStatus.UNAUTHORIZED.name());
        doThrow(httpClientErrorException).when(restTemplate).exchange(
                Mockito.eq("https://reqres.in/api/users"),
                Mockito.eq(HttpMethod.GET),
                any(HttpEntity.class),
                Mockito.eq(User.class));
        assertThrows(RestClientException.class,userService::getRequest);

    }

    @Test
    public void getStatusOk(){
        ResponseEntity<User> responseUser = new ResponseEntity<>(HttpStatus.OK);
        doReturn(responseUser).when(restTemplate).exchange(
                Mockito.eq("https://reqres.in/api/users"),
                Mockito.eq(HttpMethod.GET),
                any(HttpEntity.class),
                Mockito.eq(User.class));
        ResponseEntity<User> response = userService.getRequest();
        System.out.println(response.getStatusCode());
        assertEquals(response,responseUser);
        assertEquals(response.getBody(),responseUser.getBody());

        Mockito.verify(restTemplate)
                .exchange(
                        any(String.class),
                        any(HttpMethod.class),
                        entityCaptor.capture(),
                        any(Class.class));

        HttpEntity entityCaptured = entityCaptor.getValue();
        assertNull((entityCaptured.getBody()));

    }

}
