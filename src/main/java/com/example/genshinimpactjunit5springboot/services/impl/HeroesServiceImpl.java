package com.example.genshinimpactjunit5springboot.services.impl;

import com.example.genshinimpactjunit5springboot.models.Hero;
import com.example.genshinimpactjunit5springboot.repositories.HeroesRepository;
import com.example.genshinimpactjunit5springboot.services.interfaces.HeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HeroesServiceImpl implements HeroesService {

    private final HeroesRepository genshinHeroesRepository;

    @Autowired
    public HeroesServiceImpl(HeroesRepository genshinHeroesRepository) {
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

    public Hero save(Hero genshinHero) {
       return genshinHeroesRepository.save(genshinHero);
    }

    public Hero update(int id, Hero updatedGenshinHero) {
        updatedGenshinHero.setId(id);
        Hero hero =  genshinHeroesRepository.save(updatedGenshinHero);
        return hero;
    }

    public Hero updateHeroNameById(int id, String name) {
        return genshinHeroesRepository.updateHeroNameById(name, id);
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
