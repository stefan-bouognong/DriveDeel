package com.drivedreal.drivedreal.bridge.abstraction;

import com.drivedreal.drivedreal.bridge.implementation.RenduFormulaire;
import com.drivedreal.drivedreal.dto.FormulaireDTO;

public class FormulaireCommande extends Formulaire {

    public FormulaireCommande(RenduFormulaire impl) {
        super(impl);
    }

    @Override
    public FormulaireDTO creerChamps() {
        return impl.rendreFormulaire();
    }
}
