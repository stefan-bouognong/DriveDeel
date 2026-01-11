package com.drivedreal.drivedreal.commande;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
public class CommandeCredit extends Commande {

    @Override
    public void calculerMontant() {
        this.montantTotal = 12_000_000;
        System.out.println("Commande avec crédit | Montant : " + montantTotal);
    }

    public CommandeCredit(double montantTotal) {
        super(null, "CMD-" + System.currentTimeMillis(), montantTotal, "credit");
    }
}