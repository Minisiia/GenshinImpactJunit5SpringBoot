package com.example.genshinimpactjunit5springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "genshin_heroes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "element")
    private String element;


    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "weapon")
    private String weapon;

    @Min(value = 0, message = "genshin_region_id should be 1...5")
    @Column(name = "genshin_region_id")
    private int genshinRegionId;

    @Min(value = 4, message = "Rarity should be 4 or 5")
    @Max(value = 5, message = "Rarity should be 4 or 5")
    @Column(name = "rarity")
    private Integer rarity;

}
