package com.example.genshinimpactjunit5springboot.validators;

import com.example.genshinimpactjunit5springboot.models.Hero;
import com.example.genshinimpactjunit5springboot.validators.interfaces.HeroValidator;
import com.example.genshinimpactjunit5springboot.validators.impl.HeroValidatorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HeroValidatorTest {
    private final HeroValidator heroValidator = new HeroValidatorImpl();

    private Hero hero = new Hero();

    private Hero getHero() {
        return new Hero(1,"Diluc", "Pyro", "Claymore",1, 5);
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