package com.drivedreal.drivedreal.bridge.implementation;

import com.drivedreal.drivedreal.dto.FormulaireDTO;
import com.drivedreal.drivedreal.dto.ChampDTO;
import com.drivedreal.drivedreal.dto.BoutonDTO;

import java.util.ArrayList;
import java.util.List;

public class RenduHTML implements RenduFormulaire {

    @Override
    public FormulaireDTO rendreFormulaire() {
        // pour HTML, on peut juste renvoyer type "texte"
        List<ChampDTO> champs = new ArrayList<>();
        champs.add(new ChampDTO("texte", "client"));
        champs.add(new ChampDTO("texte", "vehicule"));
        champs.add(new ChampDTO("texte", "options"));

        List<BoutonDTO> boutons = new ArrayList<>();
        boutons.add(new BoutonDTO("Commander"));

        return new FormulaireDTO(champs, boutons);
    }
}
