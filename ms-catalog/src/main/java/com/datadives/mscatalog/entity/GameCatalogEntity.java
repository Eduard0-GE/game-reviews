package com.datadives.mscatalog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("catalog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCatalogEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> genres;
    private String publisher;
    private String developer;
    private int score;
    private int year;
}
