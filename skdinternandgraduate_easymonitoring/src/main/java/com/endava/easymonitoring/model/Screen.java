package com.endava.easymonitoring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Screen {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer projectId;
    private Integer priorityId;
    private Integer statusId;
    public Screen() {
    }


    public Screen(Integer projectId, Integer priorityId, Integer statusId) {
        this.projectId = projectId;
        this.priorityId = priorityId;
        this.statusId=statusId;

    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
