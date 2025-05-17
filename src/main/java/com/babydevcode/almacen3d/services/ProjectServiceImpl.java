package com.babydevcode.almacen3d.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babydevcode.almacen3d.Entities.ProjectEntity;
import com.babydevcode.almacen3d.dtos.ProjectDto;
import com.babydevcode.almacen3d.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> getAllProject() {
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        List<ProjectDto> projectDtos = new ArrayList<>();
        for (ProjectEntity projectEntity : projectEntities) {
            projectDtos.add(mapperEntityToDto(projectEntity));
        }
        return projectDtos;
    }

    @Override
    public ProjectDto getProject(final Long id) {
        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow();
        return mapperEntityToDto(projectEntity);
    }

    private ProjectDto mapperEntityToDto(final ProjectEntity projectEntity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(projectEntity.getId());
        projectDto.setProjectName(projectEntity.getProjectName());
        projectDto.setProjectPrice(projectEntity.getProjectPrice());
        return projectDto;
    }

}
