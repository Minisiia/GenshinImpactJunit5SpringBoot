package com.example.genshinimpactjunit5springboot.models;

import com.example.genshinimpactjunit5springboot.validators.interfaces.HeroValidator;
import com.example.genshinimpactjunit5springboot.validators.impl.HeroValidatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroTest {
    private HeroValidator heroValidator = new HeroValidatorImpl();

    private Hero hero = new Hero();

    private Hero getHero() {
        return new Hero(1,"Diluc", "Pyro", "Claymore",1, 5);
    }

    @Test
    @DisplayName("Тест для get и set Id")
    public void testGetAndSetId() {
        int id = 1;
        hero.setId(id);

        assertEquals(id, hero.getId());
    }

    @Test
    @DisplayName("Тест для get и set Name")
    public void testGetAndSetName() {
        String name = "Diluc";
        hero.setName(name);

        assertEquals(name, hero.getName());
    }

    @Test
    @DisplayName("Тест для get и set element")
    public void testGetAndSetElement() {
        String element = "Pyro";
        hero.setElement(element);

        assertEquals(element, hero.getElement());
    }

    @Test
    @DisplayName("Тест для get и set weapon")
    public void testGetAndSetWeapon() {
        String weapon = "Claymore";
        hero.setWeapon(weapon);

        assertEquals(weapon, hero.getWeapon());
    }

    @Test
    @DisplayName("Тест для get и set genshinRegionId")
    public void testGetAndSetGenshinRegionId() {
        int genshinRegionId = 4;
        hero.setGenshinRegionId(genshinRegionId);

        assertEquals(genshinRegionId, hero.getGenshinRegionId());
    }

    @Test
    @DisplayName("Тест для get и set genshinRegionId")
    public void testGetAndSetRarity() {
        int rarity = 4;
        hero.setRarity(rarity);

        assertEquals(rarity, hero.getRarity());
    }

    // тесты, которые тестируют метод на проверенный тип порожденного исключения из-за невалидных параметров, передаваемых в метод.

    @Test
    @DisplayName("Тест пустого поля Name")
    public void testSetNameInvalidEmpty() {
        hero = getHero();
        hero.setName("");
        assertThrows(IllegalArgumentException.class, () -> heroValidator.validate(hero));
    }

    @Test
    @DisplayName("Тест поле Name 1 символ")
    public void testSetNameInvalidLength() {
        hero = getHero();
        hero.setName("A");
        assertThrows(IllegalArgumentException.class, () -> heroValidator.validate(hero));
    }
    @Test
    @DisplayName("Тест поле Name 31 символ")
    public void testSetNameMaxLengthMoreThan30() {
        hero = getHero();
        hero.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertThrows(IllegalArgumentException.class, () -> heroValidator.validate(hero));
    }

    @Test
    @DisplayName("Тест поле genshinRegionId отрицательное")
    public void testSetGenshinRegionIdInvalidNegative() {
        hero = getHero();
        hero.setGenshinRegionId(-5);

        assertThrows(IllegalArgumentException.class, () -> heroValidator.validate(hero));
    }

    @Test
    @DisplayName("Тест на неправильный rarity")
    public void testSetRarityInvalid() {
        hero = getHero();
        hero.setRarity(6);
        assertThrows(IllegalArgumentException.class, () -> heroValidator.validate(hero));
    }

}