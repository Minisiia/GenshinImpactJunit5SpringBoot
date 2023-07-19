package com.example.genshinimpactjunit5springboot.services.impl;

import com.example.genshinimpactjunit5springboot.models.Region;
import com.example.genshinimpactjunit5springboot.repositories.RegionsRepository;
import com.example.genshinimpactjunit5springboot.services.interfaces.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegionsServiceImpl implements RegionsService {

    private final RegionsRepository genshinRegionsRepository;

    @Autowired
    public RegionsServiceImpl(RegionsRepository genshinRegionsRepository) {
        this.genshinRegionsRepository = genshinRegionsRepository;
    }

    public List<Region> findAll() {
        return genshinRegionsRepository.findAll();
    }

    public Region findOne(int id) {
        Optional<Region> foundRegion = genshinRegionsRepository.findById(id);
        return foundRegion.orElse(null);
    }

    public void save(Region genshinRegion) {
        genshinRegionsRepository.save(genshinRegion);
    }

    public void update(int id, Region genshinRegion) {
        genshinRegion.setId(id);
        genshinRegionsRepository.save(genshinRegion);
    }

    public void delete(int id) {
        genshinRegionsRepository.deleteById(id);
    }

    public List<String> getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(String location) {
        return genshinRegionsRepository.getGenshinHeroesNamesFromSomeLocationAndSomeWeapon(location);
    }
}
