package com.example.genshinimpactjunit5springboot.services;

import com.example.genshinimpactjunit5springboot.models.Hero;
import com.example.genshinimpactjunit5springboot.repositories.HeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroesService {

    private final HeroesRepository genshinHeroesRepository;

    @Autowired
    public HeroesService(HeroesRepository genshinHeroesRepository) {
        this.genshinHeroesRepository = genshinHeroesRepository;
    }

    public List<Hero> findAll() {
        return genshinHeroesRepository.findAll();
    }

    public Hero findOne(int id) {
        Optional<Hero> foundPerson = genshinHeroesRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Hero> findByName(String name) {
        List<Hero> findByNameList = genshinHeroesRepository.findByName(name);
        return findByNameList;
    }

    public List<Hero> findByRarity(Integer rarity) {
        List<Hero> findByRarityList = genshinHeroesRepository.findByRarity(rarity);
        return findByRarityList;
    }

    public void save(Hero genshinHero) {
        genshinHeroesRepository.save(genshinHero);
    }

    public void update(int id, Hero updatedGenshinHero) {
        updatedGenshinHero.setId(id);
        genshinHeroesRepository.save(updatedGenshinHero);
    }

    public void updateHeroNameById(int id, String name) {
        genshinHeroesRepository.updateHeroNameById(name, id);
    }

    public void delete(int id) {
        genshinHeroesRepository.deleteById(id);
    }

    public void deleteByName(String name) {
        genshinHeroesRepository.deleteByName(name);
    }

    public List<String> getGenshinHeroesWithSomeWeapon(String location, String weapon) {
        return genshinHeroesRepository.getGenshinHeroesWithSomeWeaponFromSomeRegion(location, weapon);
    }
}
