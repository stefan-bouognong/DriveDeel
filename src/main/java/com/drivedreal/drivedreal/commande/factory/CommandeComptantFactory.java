package com.drivedreal.drivedreal.commande.factory;

import org.springframework.stereotype.Component;
import com.drivedreal.drivedreal.commande.Commande;
import com.drivedreal.drivedreal.commande.CommandeComptant;

//@Component("comptant")
public class CommandeComptantFactory implements CommandeFactory {

    @Override
    public Commande creerCommande() {
        return new CommandeComptant();
    }

    @Override
    public Commande creerCommande(double montantTotal) {
        return new CommandeComptant(montantTotal);
    }
}