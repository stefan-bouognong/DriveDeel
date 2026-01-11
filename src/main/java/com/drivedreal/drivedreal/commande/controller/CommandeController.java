package com.drivedreal.drivedreal.commande.controller;

import com.drivedreal.drivedreal.commande.Commande;
import com.drivedreal.drivedreal.commande.dto.CreerCommandeDto;
import com.drivedreal.drivedreal.commande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.findAll();
    }

    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.findById(id);
    }

    @PostMapping("/comptant")
    public Commande createComptantCommande(@RequestBody CreerCommandeDto dto) {
        return commandeService.createCommandeComptant(dto);
    }

    @PostMapping("/credit")
    public Commande createCreditCommande(@RequestBody CreerCommandeDto dto) {
        return commandeService.createCommandeCredit(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteById(id);
    }


    @PostMapping("/{id}/cancel")
    public Commande cancelCommande(@PathVariable Long id) {
        return commandeService.cancelCommande(id);
    }

    @PostMapping("/{id}/succeed")
    public Commande succeedCommande(@PathVariable Long id) {
        return commandeService.approveCommande(id);
    }

    @PostMapping("/{id}/failed")
    public Commande failedCommande(@PathVariable Long id) {
        return commandeService.rejectCommande(id);
    }

}
