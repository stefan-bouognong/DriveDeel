package com.drivedreal.drivedreal.vehicule;

import com.drivedreal.drivedreal.vehicule.iterator.CatalogueIterator;
import com.drivedreal.drivedreal.vehicule.iterator.VehiculeIterator;

import java.util.ArrayList;
import java.util.List;

public class Catalogue {

    private List<Vehicule> vehicules = new ArrayList<>();

    public void ajouterVehicule(Vehicule vehicule) {
        vehicules.add(vehicule);
    }

    public VehiculeIterator creerIterator() {
        return new CatalogueIterator(vehicules);
    }
}