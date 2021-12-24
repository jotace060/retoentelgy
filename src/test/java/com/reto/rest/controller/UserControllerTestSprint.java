package com.reto.rest.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reto.rest.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Test de integración
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTestSprint {
    @MockBean
    RestTemplate restTemplate = null;

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void statusOkTest() throws Exception {
        Object randomObj = new Object() {
            public final Integer page = 1;
        };
        String json = objectMapper.writeValueAsString(randomObj);
        mvc.perform(post("/api/users/modify")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("user:{noop}password".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void badRequestTest() throws Exception {
        mvc.perform(post("/api/users/modify")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("user:{noop}password".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((byte[]) null))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void unauthotizedTest() throws Exception {
        mvc.perform(post("/api/users/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((byte[]) null))
                .andExpect(status().isUnauthorized())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void notFoundTest() throws Exception {
        Object randomObj = new Object() {
            public final Integer page = 1;
        };
        String json = objectMapper.writeValueAsString(randomObj);
        mvc.perform(post("/api/users/modify2")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("user:{noop}password".getBytes()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

}
