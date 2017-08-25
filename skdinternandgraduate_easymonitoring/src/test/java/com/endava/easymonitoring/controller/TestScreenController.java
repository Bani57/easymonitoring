package com.endava.easymonitoring.controller;

import com.endava.easymonitoring.model.Screen;
import com.endava.easymonitoring.repository.ScreenRepository;
import com.endava.easymonitoring.service.ScreenService;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestScreenController {
    @Mock
    private ScreenRepository screenRepository;
    @Mock
    private ScreenService screenService;
    @InjectMocks
    private ScreenController screenController;
    private Screen screen;
    private List<Screen> screenList;
    private Gson gson;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(screenController).build();
        gson=new Gson();
        screen=new Screen();
        screen.setProjectId(1);
        screen.setPriorityId(1);
        screen.setStatusId(1);
        screenList=new ArrayList<>();
        screenList.add(screen);
        screenList.add(screen);
    }

    @Test
    public void testSaveScreen() throws Exception {
        String json=gson.toJson(screen);
        mockMvc.perform(post("/rest/api/screens/set")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetScreens() throws Exception {
        String json=gson.toJson(screenList);
        when(screenService.getAllScreensForProject(1)).thenReturn(screenList);
        mockMvc.perform(get("/rest/api/{projectId}/screens",1)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isOk());
        List<Screen> result=screenController.listScreen(1);
        Assert.assertEquals(2,result.size());
    }

    @Test
    public void testGetScreensNegativeProjectIdNonExistent() throws Exception {
        String json=gson.toJson(screenList);
        when(screenService.getAllScreensForProject(4)).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/rest/api/{projectId}/screens",4)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andExpect(status().isOk());
        List<Screen> result=screenController.listScreen(4);
        Assert.assertEquals(0,result.size());
    }
}
