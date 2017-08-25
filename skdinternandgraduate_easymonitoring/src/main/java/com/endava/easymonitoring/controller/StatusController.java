package com.endava.easymonitoring.controller;

import com.endava.easymonitoring.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/rest/api")
public class StatusController {

    private final org.springframework.core.env.Environment environment;

    @Autowired
    public StatusController(org.springframework.core.env.Environment environment) {
        this.environment = environment;
    }


    @RequestMapping("/{projectId}/statuses")
    ResponseEntity<List<Status>> getStatuses(@RequestHeader(value = "JSESSIONID") String sessionId, @PathVariable String projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("JSESSIONID", sessionId);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(environment.getProperty("url.getStatus"),projectId);
        try {
            ResponseEntity<List<Status>> statuses = restTemplate.exchange(url,
                    HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<Status>>() {
                    });

            return statuses;
        }
        catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }


}

