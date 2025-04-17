package com.babydevcode.almacen3d.services;

import java.util.List;

import com.babydevcode.almacen3d.dtos.ProjectDto;

public interface ProjectService {

    List<ProjectDto> getAllProject();

    ProjectDto getProject(Long id);
    
}
