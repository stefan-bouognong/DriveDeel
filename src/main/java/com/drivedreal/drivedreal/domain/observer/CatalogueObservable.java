package com.drivedreal.drivedreal.domain.observer;

public interface CatalogueObservable {
    void addObserver(CatalogueObserver observer);
    void removeObserver(CatalogueObserver observer);
    void notifyObservers(String message);
}
