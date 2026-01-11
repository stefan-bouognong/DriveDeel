package com.drivedreal.drivedreal.commande;

import com.drivedreal.drivedreal.commande.enums.StatutCommande;
import com.drivedreal.drivedreal.commande.enums.TypeCommande;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@ToString(callSuper = true)
public class CommandeCredit extends Commande {

    @Override
    public void calculerMontant() {
        this.montantTotal = 12_000_000;
        System.out.println("Commande avec crédit | Montant : " + montantTotal);
    }

    public CommandeCredit() {
        super();
    }

    public CommandeCredit(double montantTotal) {
        super(null, "CMD-" + System.currentTimeMillis(), montantTotal, TypeCommande.CREDIT.getValue(), StatutCommande.PENDING.getValue());
    }
}