package com.springboot.University.Service;

import com.springboot.University.Entity.Professor;
import com.springboot.University.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class iProfessorService implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public Professor createProfessor(Professor professor) {
        Professor savedProfessor = professorRepository.save(professor);
        return savedProfessor;
    }

    @Override
    public Professor updateProfessorById(Long id, Professor professor) {
        Optional<Professor> existingProfessorOptional = professorRepository.findById(id);
        if(existingProfessorOptional.isPresent()){
            Professor existingProfessor = existingProfessorOptional.get();
            existingProfessor.setName(professor.getName());
            existingProfessor.setDepartment(professor.getDepartment());

            Professor updatedProfessor = professorRepository.save(existingProfessor);
            return updatedProfessor;
        }
        return null;
    }

    @Override
    public boolean deleteProfessorById(Long id) {
        if(professorRepository.existsById(id)){
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
