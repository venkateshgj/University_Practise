package com.springboot.University.Controller;

import com.springboot.University.DTO.CourseDTO;
import com.springboot.University.Service.CourseService;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

//    @InjectMocks
//    private CourseController courseController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CourseService courseServiceMock;

//    @Test
    public void getCourseBasedOnIdTest() throws Exception {
        CourseDTO courseDtoMock = new CourseDTO();
        courseDtoMock.setId(1L);
        courseDtoMock.setTitle("DSA");
        courseDtoMock.setDepartment("CSE");
        courseDtoMock.setCredits(4);
        courseDtoMock.setProfessorId(1L);
        courseDtoMock.setStudentIds(List.of(1L, 2L, 3L));

        when(courseServiceMock.getCourseById(1L)).thenReturn(courseDtoMock);

        mockMvc.perform(get("/university/api/v1/course/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

    }

//    @Test
    public void getCourseBasedOnIdNullCourseIdTest() throws Exception {
        when(courseServiceMock.getCourseById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/university/api/v1/course/8").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
