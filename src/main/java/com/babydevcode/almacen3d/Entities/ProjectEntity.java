package com.babydevcode.almacen3d.Entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private Integer priceOfDollar;

    private String time;

    private Double timeInDouble;

    private Double projectPrice;

    private LocalDate createDate;

    private LocalDate modificateDate;

}
