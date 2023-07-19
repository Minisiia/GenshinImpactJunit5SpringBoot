package com.example.genshinimpactjunit5springboot.services.interfaces;

import com.example.genshinimpactjunit5springboot.models.Region;

import java.util.List;
import java.util.Optional;

public interface RegionsService {
   List<Region> findAll();

   Region findOne(int id);

    void save(Region genshinRegion);

    void update(int id, Region genshinRegion);

   void delete(int id);

    List<String> getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(String location);
}
