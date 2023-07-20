package com.example.genshinimpactjunit5springboot.repositories;

import com.example.genshinimpactjunit5springboot.models.Hero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
Параметр replace аннотации @AutoConfigureTestDatabase определяет,
должна ли тестовая база данных заменить основную базу данных или
нет.
Значение AutoConfigureTestDatabase.Replace.NONE указывает, что
тестовая база данных не должна заменять основную базу данных.
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HeroesRepositoryTest {
    @Autowired
    HeroesRepository heroesRepository;

    private final int heroId = 1;

    @Test
    public void saveHeroTest() {
        Hero hero = new Hero("Diluc", "Pyro", "Claymore",1, 5);
        heroesRepository.save(hero);
        assertTrue(hero.getId() > 0);
    }
    @Test
    public void getHeroTest(){
        Hero hero  = getHeroById();
        assertEquals(heroId,hero.getId());
    }

    @Test
    public void getHeroesListTest(){
        List<Hero> heroList = heroesRepository.findAll();
        assertTrue(heroList.size() > 0);
    }


    @Test
    public void updateEmployeeTest(){
        Hero hero = getHeroById();
        hero.setElement("Dendro");
        Hero heroUpdated =  heroesRepository.save(hero);
        assertEquals(heroUpdated.getElement(),"Dendro");
    }

    @Test
    public void deleteEmployeeTest(){
        Hero hero = getHeroById();
        heroesRepository.delete(hero);

        Hero emptyHero = null;

        Optional<Hero> heroOptional = heroesRepository.findById(1);
        if(heroOptional.isPresent()){
            emptyHero = heroOptional.get();
        }

        assertNull(emptyHero);
    }

    private Hero getHeroById() {
        return heroesRepository.findById(heroId)
                .orElseThrow(() -> new RuntimeException("Hero not found"));
    }
}