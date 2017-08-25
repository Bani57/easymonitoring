package com.endava.easymonitoring.controller;

import com.endava.easymonitoring.model.Status;
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

public class TestStatusController {
    @Mock
    private Environment environment;
    @InjectMocks
    private StatusController statusController;

    private MockMvc mockMvc;
    private Status status;
    private String sessionid;
    private String projectid;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(statusController).build();
        sessionid = "1";
        projectid = "123";
        status = new Status();
        status.setId("1");
        status.setName("OPEN");
        status.setDescription("Task is open");
    }

    @Test
    public void TestStatusControllerInvalidSessionReturnsAnauthorized() throws Exception {
        List<Status> statuses = new ArrayList<>();
        statuses.add(status);

        when(environment.getProperty("url.getStatus")).thenReturn("http://localhost:8050/rest/api/123/statuses");


        mockMvc.perform(get("/rest/api/{projectId}/statuses", projectid).header("JSESSIONID", sessionid)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isUnauthorized());
    }
}
