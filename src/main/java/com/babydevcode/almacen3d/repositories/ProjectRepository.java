package com.babydevcode.almacen3d.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.babydevcode.almacen3d.Entities.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}
