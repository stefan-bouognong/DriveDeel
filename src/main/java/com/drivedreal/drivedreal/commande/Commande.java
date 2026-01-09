package com.drivedreal.drivedreal.commande;

public abstract class Commande {

    protected String idCommande;
    protected double montantTotal;

    public Commande() {
        this.idCommande = "CMD-" + System.currentTimeMillis();
    }

    public abstract void calculerMontant();

    public String getIdCommande() {
        return idCommande;
    }
}
