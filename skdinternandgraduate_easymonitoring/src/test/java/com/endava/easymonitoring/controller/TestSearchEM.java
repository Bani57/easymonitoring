package com.endava.easymonitoring.controller;

import com.endava.easymonitoring.model.Priority;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestSearchEM {
    @Mock
    private Environment environment;
    @InjectMocks
    private PriorityController priorityController;

    private MockMvc mockMvc;
    private Priority priority;
    private String sessionid;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(priorityController).build();
        sessionid = "1";
        priority = new Priority();
        priority.setColor("#ffffff");
        priority.setDescription("low priority");
        priority.setName("LOW");
    }

    @Test
    public void TestPriorityControllerInvalidSessionReturnsAnauthorized() throws Exception {
        List<Priority> priorities = new ArrayList<>();
        priorities.add(priority);

        when (environment.getProperty("url.getPriority")).thenReturn("http://localhost:8050/rest/api/priority");

        mockMvc.perform(get("/rest/api/priority").header("JSESSIONID", sessionid)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isUnauthorized());
    }

}
