package com.datadives.mscatalog.controller;

import com.datadives.mscatalog.entity.GameCatalogEntity;
import com.datadives.mscatalog.repository.GameCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GameCatalogController {

    @Autowired
    private GameCatalogRepository repository;

    @PostMapping("/game")
    public ResponseEntity<GameCatalogEntity> save(@RequestBody GameCatalogEntity ent){
        return ResponseEntity.ok(repository.save(ent));
    }

    @GetMapping("/game/name/{name}")
    public ResponseEntity<GameCatalogEntity> findByName(@PathVariable String name){
        return ResponseEntity.ok(repository.findByName(name));
    }

    @GetMapping("/games")
    public ResponseEntity<List<GameCatalogEntity>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/games/genre/{genre}")
    public ResponseEntity<List<GameCatalogEntity>> findAllByGenres(@PathVariable String genre){
        return ResponseEntity.ok(repository.findAllByGenres(genre));
    }
}
