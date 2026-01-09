package com.drivedreal.drivedreal.bridge.abstraction;

import com.drivedreal.drivedreal.bridge.implementation.RenduFormulaire;
import com.drivedreal.drivedreal.dto.BoutonDTO;
import com.drivedreal.drivedreal.dto.ChampDTO;
import com.drivedreal.drivedreal.dto.FormulaireDTO;

import java.util.ArrayList;
import java.util.List;

public class FormulaireRecherche extends Formulaire {

    public FormulaireRecherche(RenduFormulaire impl) {
        super(impl);
    }

    @Override
    public FormulaireDTO creerChamps() {
        List<ChampDTO> champs = new ArrayList<>();
        champs.add(new ChampDTO("texte", "motsCles"));

        List<BoutonDTO> boutons = new ArrayList<>();
        boutons.add(new BoutonDTO("Rechercher"));

        return new FormulaireDTO(champs, boutons);
    }
}
