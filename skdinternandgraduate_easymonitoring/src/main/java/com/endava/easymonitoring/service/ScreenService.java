package com.endava.easymonitoring.service;

import com.endava.easymonitoring.model.Screen;
import com.endava.easymonitoring.repository.ScreenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenService {
    private ScreenRepository screenRepository;

    @Autowired
    public ScreenService(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }
    public void saveScreen(Screen savedScreen)
    {
        screenRepository.save(savedScreen);
    }
    public List<Screen> getAllScreensForProject(Integer project_id)
    {
        return screenRepository.findAllByProjectId(project_id);
    }
}
