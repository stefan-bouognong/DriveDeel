package com.drivedreal.drivedreal.commande.service;

import com.drivedreal.drivedreal.commande.Commande;
import com.drivedreal.drivedreal.commande.CommandeComptant;
import com.drivedreal.drivedreal.commande.CommandeCredit;
import com.drivedreal.drivedreal.commande.dto.CreerCommandeDto;
import com.drivedreal.drivedreal.commande.enums.StatutCommande;
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

    public Commande createCommandeCredit(CreerCommandeDto dto) {
        CommandeCreditFactory factory = new CommandeCreditFactory();
        Commande commandeCredit = factory.creerCommande(dto.getMontantTotal());
        return commandeRepository.save(commandeCredit);
    }

    public Commande createCommandeComptant(CreerCommandeDto dto) {
        CommandeComptantFactory factory = new CommandeComptantFactory();
        Commande commandeComptant = factory.creerCommande(dto.getMontantTotal());
        return commandeRepository.save(commandeComptant);
    }
    public void deleteById(Long id) {
        commandeRepository.deleteById(id);
    }

    public Commande approveCommande(Long id) {
        Commande commande = findById(id);
        if (commande == null) {
            throw new IllegalArgumentException("Commande not found with id: " + id);
        }
        if (StatutCommande.SUCCEED.getValue().equalsIgnoreCase(commande.getStatut())) {
            throw new IllegalStateException("Commande is already approved.");
        }
        if (StatutCommande.FAILED.getValue().equalsIgnoreCase(commande.getStatut()) || 
            StatutCommande.CANCELLED.getValue().equalsIgnoreCase(commande.getStatut())) {
            throw new IllegalStateException("Cannot approve a commande that is rejected or cancelled.");
        }
        commande.setStatut(StatutCommande.SUCCEED.getValue());
        return commandeRepository.save(commande);
    }

    public Commande cancelCommande(Long id) {
        Commande commande = findById(id);
        if (commande == null) {
            throw new IllegalArgumentException("Commande not found with id: " + id);
        }
        if (StatutCommande.CANCELLED.getValue().equalsIgnoreCase(commande.getStatut())) {
            throw new IllegalStateException("Commande is already cancelled.");
        }
        if (StatutCommande.SUCCEED.getValue().equalsIgnoreCase(commande.getStatut())) {
            throw new IllegalStateException("Cannot cancel a commande that has already succeeded.");
        }
        commande.setStatut(StatutCommande.CANCELLED.getValue());
        return commandeRepository.save(commande);
    }

    public Commande rejectCommande(Long id) {
        Commande commande = findById(id);
        if (commande == null) {
            throw new IllegalArgumentException("Commande not found with id: " + id);
        }
        if (StatutCommande.FAILED.getValue().equalsIgnoreCase(commande.getStatut())) {
            throw new IllegalStateException("Commande is already rejected.");
        }
        if (StatutCommande.SUCCEED.getValue().equalsIgnoreCase(commande.getStatut())) {
            throw new IllegalStateException("Cannot reject a commande that has already succeeded.");
        }
        commande.setStatut(StatutCommande.FAILED.getValue());
        return commandeRepository.save(commande);
    }
}
