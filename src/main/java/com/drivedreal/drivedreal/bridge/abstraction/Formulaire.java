package com.drivedreal.drivedreal.bridge.abstraction;

import com.drivedreal.drivedreal.bridge.implementation.RenduFormulaire;
import com.drivedreal.drivedreal.dto.FormulaireDTO;

public abstract class Formulaire {

    protected RenduFormulaire impl;

    public Formulaire(RenduFormulaire impl) {
        this.impl = impl;
    }

    public abstract FormulaireDTO creerChamps();

    public FormulaireDTO afficher() {
        return creerChamps();
    }
}
