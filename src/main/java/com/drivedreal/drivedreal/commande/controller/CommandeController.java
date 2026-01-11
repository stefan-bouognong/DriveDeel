package com.drivedreal.drivedreal.commande.controller;

import com.drivedreal.drivedreal.commande.Commande;
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

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteById(id);
    }
}
