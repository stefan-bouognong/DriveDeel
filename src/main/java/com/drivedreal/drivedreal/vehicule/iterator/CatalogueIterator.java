package com.drivedreal.drivedreal.vehicule.iterator;

import java.util.List;

import com.drivedreal.drivedreal.vehicule.Vehicule;

public class CatalogueIterator implements VehiculeIterator {

    private final List<Vehicule> vehicules;
    private int index = 0;

    public CatalogueIterator(List<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    @Override
    public boolean hasNext() {
        return index < vehicules.size();
    }

    @Override
    public Vehicule next() {
        return vehicules.get(index++);
    }
}