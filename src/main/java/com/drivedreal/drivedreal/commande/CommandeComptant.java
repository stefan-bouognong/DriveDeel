package com.drivedreal.drivedreal.commande;

import com.drivedreal.drivedreal.commande.enums.StatutCommande;
import com.drivedreal.drivedreal.commande.enums.TypeCommande;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@ToString(callSuper = true)
public class CommandeComptant extends Commande {

    @Override
    public void calculerMontant() {
        this.montantTotal = 10_000_000;
        System.out.println("Commande au comptant | Montant : " + montantTotal);
    }

    public CommandeComptant() {
        super();
    }

    public CommandeComptant(double montantTotal) {
        super(null, "CMD-" + System.currentTimeMillis(), montantTotal, TypeCommande.COMPTANT.getValue(), StatutCommande.PENDING.getValue());
    }   
}
