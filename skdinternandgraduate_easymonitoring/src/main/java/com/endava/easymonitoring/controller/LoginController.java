package com.endava.easymonitoring.controller;


import com.endava.easymonitoring.model.SessionModel;
import com.endava.easymonitoring.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {

    private final org.springframework.core.env.Environment environment;

    @Autowired
    public LoginController(Environment environment) {
        this.environment = environment;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/rest/login/session")
    public ResponseEntity<SessionModel> login(@RequestBody UserModel userModel) {

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<SessionModel> loginResponse = restTemplate.postForEntity(environment.getProperty("url.login"), userModel, SessionModel.class);
            return loginResponse;
        }
        catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }
}
