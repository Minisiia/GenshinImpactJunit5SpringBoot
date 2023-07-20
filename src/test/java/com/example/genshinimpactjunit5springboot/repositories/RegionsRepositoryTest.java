package com.example.genshinimpactjunit5springboot.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionsRepositoryTest {
    @Autowired
    RegionsRepository regionsRepository;

    @Test
    void getGenshinHeroesNamesFromSomeLocationAndSomeWeapon() {
        String location = "Some location";
        List<String> list = regionsRepository.getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(location);
        assertNotNull(list);
    }
}