package com.springboot.University.Util;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.Entity.Course;
import com.springboot.University.Entity.Professor;
import com.springboot.University.Entity.Student;
import com.springboot.University.Exceptions.InvalidRequestException;
import com.springboot.University.Exceptions.ResourceNotFoundException;
import com.springboot.University.Repository.ProfessorRepository;
import com.springboot.University.Repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MapperTest {
    @InjectMocks
    private Mapper mapper;

    @Mock
    private ProfessorRepository professorRepositoryMock;

    @Mock
    private StudentRepository studentRepositoryMock;

    @Test
    public void cDtoToCReturnCourseTest() {
        CourseDTO courseDTOMock = new CourseDTO();
        courseDTOMock.setId(2L);
        courseDTOMock.setCredits(2);
        courseDTOMock.setTitle("DS");
        courseDTOMock.setDepartment("CSE");

        courseDTOMock.setProfessorId(1L);

        List<Long> studentIds = List.of(1L, 2L);

        courseDTOMock.setStudentIds(studentIds);

        // Web Mock MVC - it is used to mock an api call

        // In order to mock any other Methods, we will be using
        // when( objectOfClass.methodName(parameters)).thenReturn(returnValue);

        Professor professorMock = new Professor();
        professorMock.setId(1L);
        professorMock.setName("Ravi");
        professorMock.setDepartment("CSE");

        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(professorMock));

        List<Student> studentListMock = new ArrayList<>();

        Student student1 = new Student("Ram","CSE", 2026);
        student1.setId(1L);

        Student student2 = new Student("Raju","CSE", 2028);
        student1.setId(2L);

        studentListMock.add(student1);
        studentListMock.add(student2);


        when(studentRepositoryMock.findAllById(studentIds)).thenReturn(studentListMock);


        Course response = mapper.courseDTOToCourse(courseDTOMock);


        assertNotNull(response);
        assertEquals(Course.class , response.getClass());

//        assertEquals(course,mapper.courseDTOToCourse(courseDTOMock) );
//
//        String response1 = mapper.getString();
//        assertNotNull();  // return type is not null
//        assertNull(); //expects return type null
//        assertTrue(); // expects boolean return type with value true
//        assertEquals("Done", response1 );  // expects return to be equal to 1st parameter
    }

    @Test
    public void cDtoToCNullDto() {
        CourseDTO courseDTOMock = null;

        assertNull(mapper.courseDTOToCourse(courseDTOMock));
    }

    @Test
    public void cDtoToCNullProfessorId() {
        CourseDTO courseDTOMock = new CourseDTO();
        courseDTOMock.setId(2L);
        courseDTOMock.setCredits(2);
        courseDTOMock.setTitle("DS");
        courseDTOMock.setDepartment("CSE");

        courseDTOMock.setProfessorId(null);

        List<Long> studentIds = List.of(1L, 2L);

        courseDTOMock.setStudentIds(studentIds);

        assertThrows(InvalidRequestException.class, ()-> mapper.courseDTOToCourse(courseDTOMock) );
    }

    @Test
    public void cDtoToCNoProfessorForProfessorId() {
        CourseDTO courseDTOMock = new CourseDTO();
        courseDTOMock.setId(2L);
        courseDTOMock.setCredits(2);
        courseDTOMock.setTitle("DS");
        courseDTOMock.setDepartment("CSE");

        courseDTOMock.setProfessorId(6L);

        List<Long> studentIds = List.of(1L, 2L);

        courseDTOMock.setStudentIds(studentIds);

        Professor professorMock = new Professor();
        professorMock.setId(1L);
        professorMock.setName("Ravi");
        professorMock.setDepartment("CSE");

        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(professorMock));


        assertThrows(ResourceNotFoundException.class, ()->mapper.courseDTOToCourse(courseDTOMock));
    }

    @Test
    public void cDtoToCNullStudentIds() {
        CourseDTO courseDTOMock = new CourseDTO();
        courseDTOMock.setId(2L);
        courseDTOMock.setCredits(2);
        courseDTOMock.setTitle("DS");
        courseDTOMock.setDepartment("CSE");

        courseDTOMock.setProfessorId(1L);

        courseDTOMock.setStudentIds(null);

        Professor professorMock = new Professor();
        professorMock.setId(1L);
        professorMock.setName("Ravi");
        professorMock.setDepartment("CSE");

        when(professorRepositoryMock.findById(1L)).thenReturn(Optional.of(professorMock));


        assertThrows(InvalidRequestException.class, () -> mapper.courseDTOToCourse(courseDTOMock));
    }
}
