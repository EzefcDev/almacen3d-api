package com.babydevcode.almacen3d.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.babydevcode.almacen3d.dtos.ProjectDto;
import com.babydevcode.almacen3d.exception.ProductNotFoundException;
import com.babydevcode.almacen3d.services.ProjectService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping()
    public ResponseEntity<List<ProjectDto>> getAllProject() {
        return ResponseEntity.ok().body(projectService.getAllProject());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleNotFound(Exception e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
