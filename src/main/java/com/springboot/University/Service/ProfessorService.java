package com.springboot.University.Service;

import com.springboot.University.Entity.Professor;

import java.util.List;

public interface ProfessorService {

    List<Professor> getAllProfessors();

    Professor getProfessorById(Long id);

    Professor createProfessor(Professor professor);

    Professor updateProfessorById(Long id, Professor professor);

    boolean deleteProfessorById(Long id);
}
