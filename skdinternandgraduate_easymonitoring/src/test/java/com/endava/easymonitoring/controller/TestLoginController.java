package com.endava.easymonitoring.controller;


import com.endava.easymonitoring.model.UserModel;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestLoginController {
    @Mock
    private Environment environment;
    @InjectMocks
    private LoginController loginController;
    private MockMvc mockMvc;
    private Gson gson;
    private UserModel userValid;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(loginController).build();
        gson=new Gson();
        userValid=new UserModel("admin","admin");
    }

    @Test
    public void testLogin() throws Exception {
        String json=gson.toJson(userValid);
        when(environment.getProperty("url.login")).thenReturn("http://localhost:8050/rest/login/session");
        mockMvc.perform(post("/rest/login/session")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoginNegativeUsername() throws Exception {
        UserModel userInvalid=new UserModel();
        userInvalid.setUsername("andrej");
        userInvalid.setPassword(userValid.getPassword());
        String json=gson.toJson(userInvalid);
        when(environment.getProperty("url.login")).thenReturn("http://localhost:8050/rest/login/session");
        mockMvc.perform(post("/rest/login/session")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testLoginNegativePassword() throws Exception {
        UserModel userInvalid=new UserModel();
        userInvalid.setUsername(userValid.getUsername());
        userInvalid.setPassword("1234");
        String json=gson.toJson(userInvalid);
        when(environment.getProperty("url.login")).thenReturn("http://localhost:8050/rest/login/session");
        mockMvc.perform(post("/rest/login/session")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testLoginNegativeUsernameAndPassword() throws Exception {
        UserModel userInvalid=new UserModel();
        userInvalid.setUsername("andrej");
        userInvalid.setPassword("1234");
        String json=gson.toJson(userInvalid);
        when(environment.getProperty("url.login")).thenReturn("http://localhost:8050/rest/login/session");
        mockMvc.perform(post("/rest/login/session")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isUnauthorized());
    }
}
