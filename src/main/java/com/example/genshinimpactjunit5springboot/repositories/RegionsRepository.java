package com.example.genshinimpactjunit5springboot.repositories;

import com.example.genshinimpactjunit5springboot.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegionsRepository extends JpaRepository<Region, Integer> {

    @Query(value = "SELECT genshin_heroes.name FROM genshin_regions JOIN genshin_heroes ON genshin_regions.id = genshin_heroes.genshin_region_id WHERE genshin_regions.location=?;", nativeQuery = true)
    List<String> getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(String location);

}
