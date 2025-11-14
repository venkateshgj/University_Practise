package com.springboot.University.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
public class Course {

    // Many-to-One relationship with professor - many courses one professor i.e. each course has only one professor but multiple course can have same professor

    // Subash - English
    //        - Maths

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private int credits;
    private String department;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Course(String title, int credits, String department){
        this.title = title;
        this.credits = credits;
        this.department = department;
    }

    public Course() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
