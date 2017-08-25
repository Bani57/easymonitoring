package com.endava.easymonitoring.controller;


import com.endava.easymonitoring.dto.PriorityOrStatusDTO;
import com.endava.easymonitoring.dto.SearchRequest;
import com.endava.easymonitoring.model.Priority;
import com.endava.easymonitoring.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PriorityController {

    private final Environment environment;

    @Autowired
    public PriorityController(Environment environment) {
        this.environment = environment;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/rest/api/priority")
    public ResponseEntity<List<Priority>> getPriority(@RequestHeader("JSESSIONID") String jsessionid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("JSESSIONID", jsessionid);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        String url = environment.getProperty("url.getPriority");
        try {
            ResponseEntity<List<Priority>> priorities = restTemplate.exchange(url,
                    HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<Priority>>() {
                    });
            return priorities;
        }
        catch (HttpStatusCodeException e) {
            return new ResponseEntity<>(e.getStatusCode());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rest/api/search/{projectId}")
    public ResponseEntity<List<PriorityOrStatusDTO>> searchProjectID(@RequestHeader("JSESSIONID") String jsessionid,
                                                                     @PathVariable String projectId, @RequestBody String type) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("JSESSIONID", jsessionid);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        if (type.equals("status")) {
            String urlStatuses = String.format(environment.getProperty("url.getStatus"),projectId);
            String urlSearchStatus = String.format(environment.getProperty("url.searchStatus"),projectId); // url to jiramock
            try {
                ResponseEntity<List<Status>> response = restTemplate.exchange(urlStatuses,
                        HttpMethod.GET, entity,
                        new ParameterizedTypeReference<List<Status>>() {
                        });
                List<Status> statuses = response.getBody();

                return calculate(urlStatuses, urlSearchStatus,
                        statuses.stream().map(Status::getName).collect(Collectors.toList()),
                        headers, restTemplate);
            }
            catch (HttpStatusCodeException e) {
                return new ResponseEntity<>(e.getStatusCode());
            }
        } else {
            String urlPriorities = environment.getProperty("url.getPriority");
            String urlSearchPriority = String.format(environment.getProperty("url.searchPriority"),projectId);
            try {
                ResponseEntity<List<Priority>> response = restTemplate.exchange(urlPriorities,
                        HttpMethod.GET, entity,
                        new ParameterizedTypeReference<List<Priority>>() {
                        });
                List<Priority> priorities = response.getBody();
                return calculate(urlPriorities, urlSearchPriority,
                        priorities.stream().map(Priority::getName).collect(Collectors.toList()),
                        headers, restTemplate);
            }
            catch (HttpStatusCodeException e) {
                return new ResponseEntity<>(e.getStatusCode());
            }
        }

    }


    private ResponseEntity<List<PriorityOrStatusDTO>> calculate(String urlType,
                                                                String urlSearchType,
                                                                List<String> arr,
                                                                HttpHeaders headers,
                                                                RestTemplate restTemplate) {
        List<PriorityOrStatusDTO> list = new ArrayList<>();
        arr.forEach(e -> {
            HttpEntity<SearchRequest> request = new HttpEntity<>(new SearchRequest(e),headers);
            ResponseEntity<String> response = restTemplate.postForEntity(urlSearchType,
                    request, String.class);
            list.add(new PriorityOrStatusDTO(e, response.getBody()));

        });
        ResponseEntity<List<PriorityOrStatusDTO>> response = new ResponseEntity<>(
                list, headers, HttpStatus.OK
        );
        return response;
    }





}
