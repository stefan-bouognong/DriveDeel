package com.drivedreal.drivedreal.commande.enums;

public enum TypeCommande {
    COMPTANT("COMPTANT"),
    CREDIT("CREDIT");

    private final String value;

    TypeCommande(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static TypeCommande fromValue(String value) {
        for (TypeCommande type : TypeCommande.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
