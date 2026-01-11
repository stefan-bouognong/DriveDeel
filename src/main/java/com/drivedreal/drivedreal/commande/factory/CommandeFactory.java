package com.drivedreal.drivedreal.commande.factory;

import com.drivedreal.drivedreal.commande.Commande;

public interface CommandeFactory {
    Commande creerCommande();

    Commande creerCommande(double montantTotal);
}