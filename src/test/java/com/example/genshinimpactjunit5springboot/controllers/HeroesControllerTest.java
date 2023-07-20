package com.example.genshinimpactjunit5springboot.controllers;

import com.example.genshinimpactjunit5springboot.models.Hero;
import com.example.genshinimpactjunit5springboot.services.impl.HeroesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HeroesController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class HeroesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HeroesServiceImpl heroesService;

    private Hero hero;

    @BeforeEach
    public void init() {
        hero = new Hero(1, "Liza", "Electro", "Catalyst", 1, 4);
    }

    @Test
    void indexTest() throws Exception {
        mockMvc.perform(get("/genshin-heroes"))
                .andExpect(status().isOk()).
                andExpect(view().name("genshin-heroes/index"));
    }

    @Test
    void showTest() throws Exception {
        int heroId = 1;

        when(heroesService.findOne(heroId)).thenReturn(hero);

        mockMvc.perform(get("/genshin-heroes/{id}", heroId))
                .andExpect(status().isOk())
                .andExpect(view().name("genshin-heroes/show"))
                .andExpect(model().attributeExists("hero"))
                .andExpect(model().attribute("hero", hero))
                .andDo(print());
    }

    /*
    .is2xxSuccessful()) - результат работы будет не просто ОК, а какой-то из 200х статусов.
    */
    @Test
    void newHeroTest() throws Exception {
        mockMvc.perform(get("/genshin-heroes/new"))
                .andExpect(status()
                        .is2xxSuccessful())
                .andExpect(view().name("genshin-heroes/new"));
    }

    /*
    Метод .andExpect(status().isFound()) используется для проверки статуса HTTP-ответа.
    В данном случае, он проверяет, что статус ответа соответствует коду состояния 302 (Found).
    Код состояния 302 указывает на то, что ресурс был найден,
    но требует дальнейшего перенаправления на другую страницу или URL. */

    @Test
    void createTest() throws Exception {
        mockMvc.perform(post("/genshin-heroes")
                        .flashAttr("hero", hero))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/genshin-heroes"))
                .andDo(print());

        verify(heroesService).save(hero);
    }

    @Test
    void editTest() throws Exception {
        int heroId = 1;

        when(heroesService.findOne(heroId)).thenReturn(hero);
        mockMvc.perform(get("/genshin-heroes/{id}/edit", heroId))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("hero"))
                .andExpect(model().attribute("hero", hero))
                .andExpect(view().name("genshin-heroes/edit"));
    }

    @Test
    void updateTest() throws Exception {
        int heroId = 1;
        Hero updatedHero = new Hero(heroId, "Liza", "Electro", "Catalyst", 1, 5);
        when(heroesService.findOne(heroId)).thenReturn(hero);

        mockMvc.perform(patch("/genshin-heroes/{id}", heroId)
                        .flashAttr("hero", updatedHero))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/genshin-heroes"))
                .andDo(print());

        verify(heroesService).update(heroId, updatedHero);
    }

    /*
    .andExpect(redirectedUrl("/genshin-heroes"))
     */
    @Test
    void deleteTest() throws Exception {
        int heroId = 1;
        when(heroesService.findOne(heroId)).thenReturn(hero);
        mockMvc.perform(delete("/genshin-heroes/{id}", heroId)
                        .flashAttr("hero", hero))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/genshin-heroes"))
                .andDo(print());

        verify(heroesService).delete(heroId);
    }

    /*
     шаблон "/genshin-heroes/index*" используется для проверки того, что перенаправленный URL
     начинается с "/genshin-heroes/index", и после этого могут следовать любые символы.
    */
    @Test
    void findWeaponTest() throws Exception {
        String myLocation = "SomeLocation";
        String myWeapon = "SomeWeapon";

        mockMvc.perform(post("/genshin-heroes/my-form")
                        .param("myLocation", myLocation)
                        .param("myWeapon", myWeapon))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/genshin-heroes/index*"))
                .andExpect(redirectedUrl("/genshin-heroes/index?myLocation=SomeLocation&myWeapon=SomeWeapon"))
                .andDo(print());
    }

    @Test
    void updateIndexAllHeroesTest() throws Exception {
        // Prepare test data
        List<Hero> allHeroes = new ArrayList<>();
        allHeroes.add(new Hero(1, "Diluc", "Pyro", "Claymore", 1, 5));
        allHeroes.add(new Hero(2, "Venti", "Anemo", "Bow", 2, 5));
        allHeroes.add(new Hero(3, "Xingqiu", "Hydro", "Sword", 2, 4));

        // Mock the service method calls
        when(heroesService.findAll()).thenReturn(allHeroes);

        mockMvc.perform(get("/genshin-heroes/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("genshin-heroes/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("heroes"))
                .andExpect(MockMvcResultMatchers.model().attribute("heroes", allHeroes))
                .andDo(print());


        // Verify that service methods were called with the correct parameters
        verify(heroesService).findAll();
    }

    @Test
    void updateIndexHeroesByNameListTest() throws Exception {
        List<Hero> heroesByName = new ArrayList<>();
        heroesByName.add(new Hero(1, "Diluc", "Pyro", "Claymore", 1, 5));

        when(heroesService.findByName(anyString())).thenReturn(heroesByName);

        mockMvc.perform(get("/genshin-heroes/index?myName=SomeName"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("genshin-heroes/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("heroesByNameList"))
                .andExpect(MockMvcResultMatchers.model().attribute("heroesByNameList", heroesByName));

        verify(heroesService).findByName(anyString());
    }

    @Test
    void updateIndexHeroesByWeaponListTest() throws Exception {
        List<String> heroesByWeapon = new ArrayList<>();
        heroesByWeapon.add("Kli");

        when(heroesService.getGenshinHeroesWithSomeWeapon(anyString(),anyString())).thenReturn(heroesByWeapon);

        mockMvc.perform(get("/genshin-heroes/index?myLocation=SomeLocation&myWeapon=SomeWeapon"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("genshin-heroes/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("weaponList"))
                .andExpect(MockMvcResultMatchers.model().attribute("weaponList", heroesByWeapon));

        verify(heroesService).getGenshinHeroesWithSomeWeapon(anyString(),anyString());
    }

    @Test
    void updateIndexHeroesByRarityListTest() throws Exception {
        List<Hero> heroesByRarity = new ArrayList<>();
        heroesByRarity.add(new Hero(1, "Diluc", "Pyro", "Claymore", 1, 5));

        when(heroesService.findByRarity(anyInt())).thenReturn(heroesByRarity);

        mockMvc.perform(get("/genshin-heroes/index?myRarity=5"))
                .andExpect(status().isOk())
                .andExpect(view().name("genshin-heroes/index"))
                .andExpect(model().attributeExists("heroesByRarityList"))
                .andExpect(model().attribute("heroesByRarityList", heroesByRarity));

        verify(heroesService).findByRarity(anyInt());

    }


    @Test
    void findByNameTest() throws Exception {
        String myName = "SomeName";

        mockMvc.perform(post("/genshin-heroes/find-heroes-by-name")
                        .param("myName", myName))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/genshin-heroes/index*"))
                .andExpect(redirectedUrl("/genshin-heroes/index?myName=SomeName"))
                .andDo(print());
    }

    @Test
    void findByRarityTest() throws Exception {
        int myRarity = 5;

        mockMvc.perform(post("/genshin-heroes/find-heroes-by-rarity")
                        .param("myRarity", String.valueOf(myRarity)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/genshin-heroes/index*"))
                .andExpect(redirectedUrl("/genshin-heroes/index?myRarity=5"))
                .andDo(print());
    }

    @Test
    void updateHeroNameByIdTest() throws Exception {
        String myUpdateName = "SomeUpdateName";
        int myUpdateId = 1;

        mockMvc.perform(post("/genshin-heroes/update-hero-name-by-id")
                        .param("myUpdateName", myUpdateName)
                        .param("myUpdateId", String.valueOf(myUpdateId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/genshin-heroes/index*"))
                .andExpect(redirectedUrl("/genshin-heroes/index?myUpdateName=SomeUpdateName&myUpdateId=1"))
                .andDo(print());
    }

    @Test
    void deleteByNameTest() throws Exception {
        String myDeleteName = "SomeDeleteName";

        mockMvc.perform(post("/genshin-heroes/delete-by-name")
                        .param("myDeleteName", myDeleteName))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/genshin-heroes"))
                .andDo(print());
    }
}