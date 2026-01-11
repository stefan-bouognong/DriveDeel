package com.drivedreal.drivedreal.commande.enums;

public enum StatutCommande {
    PENDING("PENDING"),
    SUCCEED("SUCCEED"),
    FAILED("FAILED"),
    CANCELLED("CANCELLED");

    private final String value;

    StatutCommande(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static StatutCommande fromValue(String value) {
        for (StatutCommande statut : StatutCommande.values()) {
            if (statut.value.equalsIgnoreCase(value)) {
                return statut;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}