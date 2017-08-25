package com.endava.easymonitoring.controller;

import com.endava.easymonitoring.model.Screen;
import com.endava.easymonitoring.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ScreenController {

    private final ScreenService screenService;

    @Autowired
    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @RequestMapping(value = "rest/api/screens/set",method= RequestMethod.POST)
    public void saveScreen(@RequestBody Screen savedScreen)
    {
        screenService.saveScreen(savedScreen);
    }

    @RequestMapping(value ="rest/api/{project_Id}/screens",method = RequestMethod.GET)
    public List<Screen> listScreen(@PathVariable("project_Id")Integer project_Id){
        return screenService.getAllScreensForProject(project_Id); }


}
