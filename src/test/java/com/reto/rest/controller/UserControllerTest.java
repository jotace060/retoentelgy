package com.reto.rest.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reto.rest.alias.Support;
import com.reto.rest.alias.Users;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private UserController controller;

    @Autowired
    private MockMvc mockMvc;

    //200OK
    @Test
    public void test1() throws Exception {
        System.out.printf(String.valueOf(mockMvc));

        Object randomObj = new Object() {
            public final Integer page = 2;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/users/modify")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(json))

                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andDo(MockMvcResultHandlers.print());
    }


    //Bad request
    @Test
    public void test2() throws Exception {
        System.out.printf(String.valueOf(mockMvc));

        Object randomObj = new Object() {
            public final Integer page = 2;
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/users/modify")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content((byte[]) null))

                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    public void test3() {
         assertThat(controller).isNotNull();

    }





}