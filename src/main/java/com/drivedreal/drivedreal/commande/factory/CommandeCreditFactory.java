package com.drivedreal.drivedreal.commande.factory;

import org.springframework.stereotype.Component;
import com.inf4067.commande.Commande;
import com.inf4067.commande.CommandeCredit;

@Component("credit")
public class CommandeCreditFactory implements CommandeFactory {

    @Override
    public Commande creerCommande() {
        return new CommandeCredit();
    }
}
