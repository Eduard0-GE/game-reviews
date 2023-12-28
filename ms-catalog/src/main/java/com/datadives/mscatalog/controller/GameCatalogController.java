package com.datadives.mscatalog.controller;

import com.datadives.mscatalog.entity.GameCatalogEntity;
import com.datadives.mscatalog.repository.GameCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameCatalogController {

    @Autowired
    private GameCatalogRepository repository;

    @PostMapping("/save")
    public ResponseEntity<GameCatalogEntity> save(@RequestBody GameCatalogEntity ent){
        return ResponseEntity.ok(repository.save(ent));
    }

    @GetMapping("/find")
    public ResponseEntity<List<GameCatalogEntity>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }
}
