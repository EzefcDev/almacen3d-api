package com.babydevcode.almacen3d.controllers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.babydevcode.almacen3d.dtos.ProjectDto;
import com.babydevcode.almacen3d.exception.ProductNotFoundException;
import com.babydevcode.almacen3d.services.ProjectService;

@WebMvcTest(controllers = ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    List<ProjectDto> mockResponse;

    ProjectDto mockDto;

    @BeforeEach
    void setup() {
        mockResponse = Arrays.asList(
            new ProjectDto(1L, "Batalla Naval", 12000.00),
            new ProjectDto(2L, "Valanceados", 9000.00)
        );

        mockDto = new ProjectDto(1L, "Batalla naval",  1200.00);
    }

    @Test
    void returnAllProjectsSuccess() throws Exception {

        //Given => Dado
        when(projectService.getAllProject()).thenReturn(mockResponse);
        
        //When => Cuando
        mockMvc.perform(MockMvcRequestBuilders.get("/projects").contentType(MediaType.APPLICATION_JSON))
        // Then => Entonces
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].projectName").value("Batalla Naval"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].projectPrice").value(12000.00));
        
        verify(projectService).getAllProject();

    }

    @Test
    void returnOneProjectSuccess() throws Exception {

        //Given
        when(projectService.getProject(1L)).thenReturn(mockDto);

        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/projects/1").contentType(MediaType.APPLICATION_JSON))
        //Then
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.projectName").value("Batalla naval"));
        
        verify(projectService).getProject(1L);
    }

    @Test
    public void getProduct_ReturnsNotFound_WhenProductDoesNotExist() throws Exception {

        //Given
        when(projectService.getProject(1L)).thenThrow(new ProductNotFoundException("Producto no encontrado"));

        //When
        mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
        //Then
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
}
