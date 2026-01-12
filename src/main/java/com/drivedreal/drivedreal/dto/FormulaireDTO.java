package com.drivedreal.drivedreal.dto;

import java.util.List;

public class FormulaireDTO {
    public List<ChampDTO> champs;
    public List<BoutonDTO> boutons;

    public FormulaireDTO(List<ChampDTO> champs, List<BoutonDTO> boutons) {
        this.champs = champs;
        this.boutons = boutons;
    }
}
