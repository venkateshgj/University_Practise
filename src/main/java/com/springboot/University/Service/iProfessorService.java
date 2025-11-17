package com.springboot.University.Service;

import com.springboot.University.DTO.ProfessorDTO;
import com.springboot.University.Entity.Professor;
import com.springboot.University.MapperUtil.Mapper;
import com.springboot.University.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class iProfessorService implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private Mapper mapper;
    @Override
    public List<ProfessorDTO> getAllProfessors() {
        List<Professor> professors = professorRepository.findAll();
        return professors.stream().map(professor -> mapper.professorToProfessorDTO(professor)).toList();
    }

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Override
    public ProfessorDTO createProfessor(ProfessorDTO professorDto) {
        Professor professor = mapper.professorDTOToProfessor(professorDto);
        Professor savedProfessor = professorRepository.save(professor);
        return mapper.professorToProfessorDTO(savedProfessor);
    }

    @Override
    public Professor updateProfessorById(Long id, ProfessorDTO professorDto) {

        Optional<Professor> existingProfessorOptional = professorRepository.findById(id);
        if(existingProfessorOptional.isPresent()){
            Professor existingProfessor = existingProfessorOptional.get();
            existingProfessor.setName(professorDto.getName());
            existingProfessor.setDepartment(professorDto.getDepartment());

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
