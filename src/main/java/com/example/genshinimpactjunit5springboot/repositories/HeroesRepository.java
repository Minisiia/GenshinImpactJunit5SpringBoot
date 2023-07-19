package com.example.genshinimpactjunit5springboot.repositories;

import com.example.genshinimpactjunit5springboot.models.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroesRepository extends JpaRepository<Hero, Integer> {

    @Query(value = "SELECT genshin_heroes.name FROM genshin_heroes JOIN genshin_regions ON genshin_regions.id = genshin_heroes.genshin_region_id WHERE genshin_regions.location=? AND genshin_heroes.weapon = ?", nativeQuery = true)
    List<String> getGenshinHeroesWithSomeWeaponFromSomeRegion(String location, String weapon);

    List<Hero> findByName(String name);

    List<Hero> findByRarity(Integer rarity);

    @Modifying
    @Query(value = "UPDATE genshin_heroes SET name = ? WHERE id = ?", nativeQuery = true)
    void updateHeroNameById(String name, int id);

    @Modifying
    @Query(value = "DELETE FROM genshin_heroes WHERE name = ?", nativeQuery = true)
    void deleteByName(String name);
}
