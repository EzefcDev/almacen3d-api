package com.babydevcode.almacen3d.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.babydevcode.almacen3d.Entities.ProjectEntity;
import com.babydevcode.almacen3d.dtos.ProjectDto;
import com.babydevcode.almacen3d.exception.ProductNotFoundException;
import com.babydevcode.almacen3d.repositories.ProjectRepository;

import net.bytebuddy.pool.TypePool.Empty;

@SpringBootTest
@ActiveProfiles("test")
public class ProjectServiceImplTest {

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectServiceImpl projectService;

    private List<ProjectEntity> projectMock;

    private ProjectEntity projecEntityMock;

    @BeforeEach
    void setup() {
        projectMock = Arrays.asList(
            new ProjectEntity(),
            new ProjectEntity()
        );

        projecEntityMock = new ProjectEntity();
        projecEntityMock.setId(1L);
        projecEntityMock.setProjectName("Batalla Naval");
        projecEntityMock.setProjectPrice(2000.00);
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

    @Test
    void testGetProject() {
        
        //Given
        when(projectRepository.findById(1L)).thenReturn(Optional.of(projecEntityMock));

        //When
        ProjectDto projectDto = projectService.getProject(1L);

        //Then
        assertEquals(1L, projectDto.getId());
        assertEquals("Batalla Naval", projectDto.getProjectName());
        assertEquals(2000.00, projectDto.getProjectPrice());

        verify(projectRepository).findById(1L);

    }

    @Test
    void testGetProjectEmpty() {
        
        //Given
        when(projectRepository.findById(4L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            projectService.getProject(4L);
        });
    }

}
