package com.drivedreal.drivedreal.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keywordValue;

    @ManyToMany(mappedBy = "keywords")
    private List<Vehicle> vehicles;

    public Long getId() { return id; }
    public String getKeywordValue() { return keywordValue; }
}
