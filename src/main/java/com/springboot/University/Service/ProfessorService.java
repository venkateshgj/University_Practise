package com.springboot.University.Service;

import com.springboot.University.DTO.ProfessorDTO;
import com.springboot.University.Entity.Professor;

import java.util.List;

public interface ProfessorService {

    List<ProfessorDTO> getAllProfessors();

    Professor getProfessorById(Long id);

    ProfessorDTO createProfessor(ProfessorDTO professorDTO);

    Professor updateProfessorById(Long id, Professor professor);

    boolean deleteProfessorById(Long id);
}
