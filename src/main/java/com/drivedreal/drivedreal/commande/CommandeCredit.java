package com.drivedreal.drivedreal.commande;

public class CommandeCredit extends Commande {

    @Override
    public void calculerMontant() {
        montantTotal = 12_000_000;
        System.out.println("Commande avec crédit | Montant : " + montantTotal);
    }
}