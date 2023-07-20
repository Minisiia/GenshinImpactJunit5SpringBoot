package com.example.genshinimpactjunit5springboot.services.impl;

import com.example.genshinimpactjunit5springboot.models.Hero;
import com.example.genshinimpactjunit5springboot.repositories.HeroesRepository;
import com.example.genshinimpactjunit5springboot.services.interfaces.HeroesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeroesServiceImplTest {
    @Mock
    private HeroesRepository heroesRepository;

    @InjectMocks
    private HeroesServiceImpl heroesService;

    private final int heroId = 1;

    @Test
    void findAllTest() {
        List<Hero> allHeroes = getHeroList();

        // Создание заглушки для возвращаемого значения peopleRepository.findAll()
        when(heroesRepository.findAll()).thenReturn(allHeroes);

        // Вызов метода findAll() в сервисном классе
        List<Hero> actualHeroes = heroesService.findAll();

        // Проверка, что возвращаемое значение соответствует ожидаемому
        assertEquals(allHeroes, actualHeroes);

        Mockito.verify(heroesRepository, times(1)).findAll();
    }

    @Test
    void findOne() {
        Hero hero = getHero();

        when(heroesRepository.findById(heroId)).thenReturn(Optional.of(hero));

        Hero actualHero = heroesService.findOne(heroId);

        assertEquals(hero, actualHero);

        verify(heroesRepository, times(1)).findById(heroId);
    }

    @Test
    void findByName() {
        String name = "Some name";
        List<Hero> heroList = getHeroList();

        when(heroesRepository.findByName(name)).thenReturn(heroList);

        List<Hero> actualHeroList = heroesService.findByName(name);

        assertEquals(heroList, actualHeroList);

        verify(heroesRepository, times(1)).findByName(name);
    }

    @Test
    void findByRarity() {
        int rarity = 5;
        List<Hero> heroList = getHeroList();

        when(heroesRepository.findByRarity(rarity)).thenReturn(heroList);

        List<Hero> actualHeroList = heroesService.findByRarity(rarity);

        assertEquals(heroList, actualHeroList);

        verify(heroesRepository, times(1)).findByRarity(rarity);
    }

    @Test
    void save() {
        Hero hero = getHero();
        when(heroesRepository.save(Mockito.any(Hero.class))).thenReturn(hero);
        Hero savedHero = heroesService.save(hero);
        assertEquals(hero, savedHero);

        verify(heroesRepository, times(1)).save(hero);
    }

    @Test
    void update() {
        Hero hero = getHero();
        Hero hero1 = new Hero(heroId, "Diluc", "Pyro", "Claymore", 1, 5);
       // when(heroesRepository.findById(heroId)).thenReturn(Optional.of(hero));
        when(heroesRepository.save(hero)).thenReturn(hero);
        Hero updatedHero = heroesService.update(heroId, hero1);
        assertNotNull(updatedHero);

    }

    @Test
    void updateHeroNameById() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteByName() {
    }

    @Test
    void getGenshinHeroesWithSomeWeapon() {
        String location = "Some location";
        String weapon = "Some weapon";
        List<String> heroList = new ArrayList<>();
        heroList.add("Kli");
        heroList.add("Venti");

        when(heroesRepository.getGenshinHeroesWithSomeWeaponFromSomeRegion(location,weapon)).thenReturn(heroList);

        List<String> actualHeroList = heroesService.getGenshinHeroesWithSomeWeapon(location,weapon);

        assertEquals(heroList, actualHeroList);

        verify(heroesRepository, times(1)).getGenshinHeroesWithSomeWeaponFromSomeRegion(location,weapon);
    }

    private Hero getHero() {
        return new Hero(1, "Diluc", "Pyro", "Claymore", 1, 5);
    }

    private List<Hero> getHeroList() {
        List<Hero> allHeroes = new ArrayList<>();
        allHeroes.add(new Hero(1, "Diluc", "Pyro", "Claymore", 1, 5));
        allHeroes.add(new Hero(2, "Venti", "Anemo", "Bow", 2, 5));
        return allHeroes;
    }
}