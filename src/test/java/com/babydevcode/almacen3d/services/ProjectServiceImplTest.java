package com.babydevcode.almacen3d.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.babydevcode.almacen3d.Entities.ProjectEntity;
import com.babydevcode.almacen3d.dtos.ProjectDto;
import com.babydevcode.almacen3d.repositories.ProjectRepository;

@SpringBootTest
public class ProjectServiceImplTest {

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectServiceImpl projectService;

    private List<ProjectEntity> projectMock;

    @BeforeEach
    void setup() {
        projectMock = Arrays.asList(
            new ProjectEntity(),
            new ProjectEntity()
        );
    }

    @Test
    void testGetAllProjectSuccess() {
        
        //Given
        when(projectRepository.findAll()).thenReturn(projectMock);

        //When
        List<ProjectDto> result = projectService.getAllProject();

        //Then
        assertEquals(2, result.size());
        verify(projectRepository).findAll();
        
    }
}
