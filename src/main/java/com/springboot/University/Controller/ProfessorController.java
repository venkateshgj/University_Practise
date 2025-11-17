package com.springboot.University.Controller;

import com.springboot.University.DTO.ProfessorDTO;
import com.springboot.University.Entity.Professor;
import com.springboot.University.Entity.Student;
import com.springboot.University.Service.iProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/university/api/v1/professors")
public class ProfessorController {
    @Autowired
    private iProfessorService professorService;

    @GetMapping("/all")
    public ResponseEntity<List<ProfessorDTO>> listAllProfessors(){
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessorBasedOnId(@PathVariable Long id){
        if(professorService.getProfessorById(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<ProfessorDTO> createNewProfessorRecord(@RequestBody ProfessorDTO professorDto){
        return ResponseEntity.ok(professorService.createProfessor(professorDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Professor> updateProfessorRecord(@RequestBody ProfessorDTO professorDto, @PathVariable Long id){
        Professor savedProfessor = professorService.updateProfessorById(id, professorDto);
        if(savedProfessor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedProfessor);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfessorRecord(@PathVariable Long id){
        if(professorService.deleteProfessorById(id) == true){
            return ResponseEntity.ok("Deleted Professor Record Successfully");
        };
        return ResponseEntity.ok("Professor Record Not Found");
    }
}
