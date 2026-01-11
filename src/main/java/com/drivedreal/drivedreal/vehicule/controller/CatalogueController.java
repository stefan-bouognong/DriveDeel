package com.drivedreal.drivedreal.vehicule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.drivedreal.drivedreal.vehicule.service.CatalogueService;
import com.drivedreal.drivedreal.vehicule.Vehicule;
import com.drivedreal.drivedreal.vehicule.iterator.VehiculeIterator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/catalogue")
public class CatalogueController {

    private final CatalogueService catalogueService;

    @Autowired
    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @PostMapping("/ajouter")
    public void ajouterVehicule(@RequestBody Vehicule vehicule) {
        catalogueService.ajouterVehicule(vehicule);
    }

    @GetMapping("/vehicules")
    public List<Vehicule> getCatalogueVehicules() {
        List<Vehicule> list = new ArrayList<>();
        VehiculeIterator iterator = catalogueService.creerIterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
