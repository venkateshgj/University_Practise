package com.springboot.University.DTO;

import lombok.Data;

import java.util.List;

//@Data
public class CourseDTO {
    private Long id;
    private String title;
    private int credits;
    private String department;
    private Long professorId;
    private List<Long> studentIds;
    public CourseDTO() {}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorsId) {
        this.professorId = professorsId;
    }
    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
