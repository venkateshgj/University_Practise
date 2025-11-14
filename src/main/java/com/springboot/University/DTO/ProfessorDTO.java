package com.springboot.University.DTO;

import lombok.Data;

import java.util.List;

//@Data
public class ProfessorDTO {
    private Long id;
    private String name;
    private String department;
    private List<Long> courseIds;

    public ProfessorDTO(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

}
