package com.drivedreal.drivedreal.commande;

import com.drivedreal.drivedreal.commande.enums.TypeCommande;
import com.drivedreal.drivedreal.commande.enums.StatutCommande;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "commande")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;
    private String numCommande = "CMD-" + System.currentTimeMillis();
    protected double montantTotal;
    private String typeCommande; // "comptant" or "credit"
    private String statut = StatutCommande.PENDING.getValue(); // statut as string

    public abstract void calculerMontant();
}
