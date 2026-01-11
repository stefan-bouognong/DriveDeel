package com.drivedreal.drivedreal.commande.service;

import com.drivedreal.drivedreal.commande.Commande;
import com.drivedreal.drivedreal.commande.CommandeComptant;
import com.drivedreal.drivedreal.commande.CommandeCredit;
import com.drivedreal.drivedreal.commande.factory.CommandeComptantFactory;
import com.drivedreal.drivedreal.commande.factory.CommandeCreditFactory;
import com.drivedreal.drivedreal.commande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    public Commande findById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande save(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande createCommandeCredit(double montantTotal) {
        CommandeCreditFactory factory = new CommandeCreditFactory();
        Commande commandeCredit = factory.creerCommande(montantTotal);
        return commandeRepository.save(commandeCredit);
    }

    public Commande createCommandeComptant(double montantTotal) {
        CommandeComptantFactory factory = new CommandeComptantFactory();
        Commande commandeComptant = factory.creerCommande(montantTotal);
        return commandeRepository.save(commandeComptant);
    }
    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }
}
