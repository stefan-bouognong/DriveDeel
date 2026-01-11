package com.drivedreal.drivedreal.vehicule.service;

import org.springframework.stereotype.Service;
import com.drivedreal.drivedreal.vehicule.Catalogue;
import com.drivedreal.drivedreal.vehicule.Vehicule;
import com.drivedreal.drivedreal.vehicule.iterator.VehiculeIterator;

@Service
public class CatalogueService {

    private final Catalogue catalogue = new Catalogue();

    public void ajouterVehicule(Vehicule vehicule) {
        catalogue.ajouterVehicule(vehicule);
    }

    public VehiculeIterator creerIterator() {
        return catalogue.creerIterator();
    }

    public Catalogue getCatalogue() {
        return catalogue;
    }
}

