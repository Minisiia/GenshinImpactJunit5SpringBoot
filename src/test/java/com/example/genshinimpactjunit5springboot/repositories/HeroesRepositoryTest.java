package com.example.genshinimpactjunit5springboot.repositories;

import com.example.genshinimpactjunit5springboot.models.Hero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
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
    @Autowired
    private DataSource dataSource; // Автоматически внедряется источник данных из контекста Spring

    private final int heroId = 1;
    private List<Hero> heroList;

    @Test
    public void testDataSource() {
        assertNotNull(dataSource);
    }

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


    @Test
    void getGenshinHeroesWithSomeWeaponFromSomeRegion() {
        String location = "Some location";
        String weapon = "Some weapon";
        List<String> list = heroesRepository.getGenshinHeroesWithSomeWeaponFromSomeRegion(location,weapon);
        assertNotNull(list);
    }

    @Test
    void findByName() {
        String name = "Some name";
        heroList = heroesRepository.findByName(name);
        assertNotNull(heroList);
    }

    @Test
    void findByRarity() {
        int rarity = 4;
        heroList = heroesRepository.findByRarity(rarity);
        assertNotNull(heroList);
    }

    @Test
    void updateHeroNameById() {
        String name = "Some name";
        Hero hero = new Hero("Diluc", "Pyro", "Claymore", 1, 5);;
        heroesRepository.save(hero);

        heroesRepository.updateHeroNameById(name, hero.getId());

        Hero updatedHero = heroesRepository.findById(hero.getId()).orElse(null);

        assertNotNull(updatedHero);
        assertEquals(hero.getName(),updatedHero.getName());
    }

    @Test
    void deleteByName() {
        heroesRepository.deleteByName("Diluc");

        Hero emptyHero = null;

        Optional<Hero> heroOptional = heroesRepository.findById(1);
        if(heroOptional.isPresent()){
            emptyHero = heroOptional.get();
        }

        assertNull(emptyHero);
    }
}