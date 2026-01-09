package com.drivedreal.drivedreal.dto;

import java.util.List;

public class ChampDTO {
    public String type;
    public String name;
    public List<String> options;

    public ChampDTO(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public ChampDTO(String type, String name, List<String> options) {
        this.type = type;
        this.name = name;
        this.options = options;
    }
}
