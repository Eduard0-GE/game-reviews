package com.datadives.mscatalog.repository;

import com.datadives.mscatalog.entity.GameCatalogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameCatalogRepository extends MongoRepository<GameCatalogEntity, String> {
    GameCatalogEntity save(GameCatalogEntity entity);

    GameCatalogEntity findByName(String name);

    List<GameCatalogEntity> findAll();

    List<GameCatalogEntity>findAllByGenres(String genre);
}
