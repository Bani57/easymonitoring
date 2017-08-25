package com.endava.easymonitoring.repository;

import com.endava.easymonitoring.model.Screen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends CrudRepository<Screen,Integer>{
    List<Screen> findAllByProjectId(Integer id);
}
