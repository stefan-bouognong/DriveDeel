package com.drivedreal.drivedreal.commande;

public class CommandeComptant extends Commande {

    @Override
    public void calculerMontant() {
        montantTotal = 10_000_000;
        System.out.println("Commande au comptant | Montant : " + montantTotal);
    }
}
