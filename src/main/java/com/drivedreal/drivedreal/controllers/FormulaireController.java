package com.drivedreal.drivedreal.controllers;

import com.drivedreal.drivedreal.bridge.abstraction.Formulaire;
import com.drivedreal.drivedreal.bridge.abstraction.FormulaireCommande;
import com.drivedreal.drivedreal.bridge.abstraction.FormulaireRecherche;
import com.drivedreal.drivedreal.bridge.implementation.RenduFormulaire;
import com.drivedreal.drivedreal.bridge.implementation.RenduHTML;
import com.drivedreal.drivedreal.bridge.implementation.RenduWidget;
import com.drivedreal.drivedreal.dto.FormulaireDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formulaire")
public class FormulaireController {

    @GetMapping("/commande/html")
    public FormulaireDTO commandeHtml() {
        RenduFormulaire rendu = new RenduHTML();
        Formulaire formulaire = new FormulaireCommande(rendu);
        return formulaire.afficher();
    }

    @GetMapping("/commande/widget")
    public FormulaireDTO commandeWidget() {
        RenduFormulaire rendu = new RenduWidget();
        Formulaire formulaire = new FormulaireCommande(rendu);
        return formulaire.afficher();
    }

    @GetMapping("/recherche/html")
    public FormulaireDTO rechercheHtml() {
        System.out.println("Endpoint /recherche/html appelé !");
        RenduFormulaire rendu = new RenduHTML();
        Formulaire formulaire = new FormulaireRecherche(rendu);
        FormulaireDTO dto = formulaire.afficher();
        System.out.println("DTO: " + dto);
        return dto;
    }


    @GetMapping("/recherche/widget")
    public FormulaireDTO rechercheWidget() {
        RenduFormulaire rendu = new RenduWidget();
        Formulaire formulaire = new FormulaireRecherche(rendu);
        return formulaire.afficher();
    }
}
