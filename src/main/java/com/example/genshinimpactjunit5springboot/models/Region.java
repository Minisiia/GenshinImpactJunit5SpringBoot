package com.example.genshinimpactjunit5springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "genshin_regions")
@Data
public class Region {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Description should not be empty")
    @Size(min = 2, max = 255, message = "Description should be between 2 and 255 characters")
    @Column(name = "description")
    private String description;


    @NotEmpty(message = "Location should not be empty")
    @Size(min = 2, max = 255, message = "Location should be between 2 and 30 characters")
    @Column(name = "location")
    private String location;

}
