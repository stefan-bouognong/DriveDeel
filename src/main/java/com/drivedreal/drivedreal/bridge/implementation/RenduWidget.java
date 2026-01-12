package com.drivedreal.drivedreal.bridge.implementation;

import com.drivedreal.drivedreal.dto.FormulaireDTO;
import com.drivedreal.drivedreal.dto.ChampDTO;
import com.drivedreal.drivedreal.dto.BoutonDTO;

import java.util.ArrayList;
import java.util.List;

public class RenduWidget implements RenduFormulaire {

    @Override
    public FormulaireDTO rendreFormulaire() {
        // version “widget” pour React
        List<ChampDTO> champs = new ArrayList<>();
        champs.add(new ChampDTO("widgetTextField", "client"));
        champs.add(new ChampDTO("widgetTextField", "vehicule"));
        champs.add(new ChampDTO("widgetTextField", "options"));

        List<BoutonDTO> boutons = new ArrayList<>();
        boutons.add(new BoutonDTO("Commander"));

        return new FormulaireDTO(champs, boutons);
    }
}
