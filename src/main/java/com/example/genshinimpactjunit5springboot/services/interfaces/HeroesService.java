package com.example.genshinimpactjunit5springboot.services.interfaces;

import com.example.genshinimpactjunit5springboot.models.Hero;

import java.util.List;
import java.util.Optional;

public interface HeroesService {
    List<Hero> findAll();

    Hero findOne(int id);

    List<Hero> findByName(String name);

    List<Hero> findByRarity(Integer rarity);

    Hero save(Hero genshinHero);

    Hero update(int id, Hero updatedGenshinHero);

    void updateHeroNameById(int id, String name);

    void delete(int id);

    void deleteByName(String name);

    List<String> getGenshinHeroesWithSomeWeapon(String location, String weapon);
}
