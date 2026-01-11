package com.drivedreal.drivedreal.commande;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
public class CommandeComptant extends Commande {

    @Override
    public void calculerMontant() {
        this.montantTotal = 10_000_000;
        System.out.println("Commande au comptant | Montant : " + montantTotal);
    }

    public CommandeComptant(double montantTotal) {
        super(null, "CMD-" + System.currentTimeMillis(), montantTotal, "comptant");
    }
}
