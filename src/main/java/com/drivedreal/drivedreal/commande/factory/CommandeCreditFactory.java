package com.drivedreal.drivedreal.commande.factory;

import org.springframework.stereotype.Component;
import com.drivedreal.drivedreal.commande.Commande;
import com.drivedreal.drivedreal.commande.CommandeCredit;

@Component("credit")
public class CommandeCreditFactory implements CommandeFactory {

    @Override
    public Commande creerCommande() {
        return new CommandeCredit();
    }

    @Override
    public Commande creerCommande(double montantTotal) {
        return new CommandeCredit(montantTotal);
    }
}
