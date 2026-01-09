package com.drivedreal.drivedreal.commande.factory;

import org.springframework.stereotype.Component;
import com.inf4067.commande.Commande;
import com.inf4067.commande.CommandeComptant;

@Component("comptant")
public class CommandeComptantFactory implements CommandeFactory {

    @Override
    public Commande creerCommande() {
        return new CommandeComptant();
    }
}