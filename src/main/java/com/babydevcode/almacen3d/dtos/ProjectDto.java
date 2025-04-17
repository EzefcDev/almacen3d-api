package com.babydevcode.almacen3d.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Long id;
    private String projectName;
    private Double projectPrice;
    
}
