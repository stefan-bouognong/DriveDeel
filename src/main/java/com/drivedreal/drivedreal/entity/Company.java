package com.drivedreal.drivedreal.entity;

import com.drivedreal.drivedreal.domain.composite.CompanyComposite;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements CompanyComposite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Renommé de id_company à id pour JPA
    private String name; // Renommé de company_name à name pour JPA
    private String address;

    @OneToMany(
        mappedBy = "parentCompany",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private List<Company> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Company parentCompany; // Référence à la compagnie parente

    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false, unique = true)
    private User customer;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "company_id") // Colonne de jointure dans la table Order
    private List<Order> orders = new ArrayList<>();

    // Méthodes spécifiques au composite
    public void addChild(Company child) {
        children.add(child);
        child.setParentCompany(this);
    }

    public void removeChild(Company child) {
        children.remove(child);
        child.setParentCompany(null);
    }

    // Implémentation des méthodes de l'interface CompanyComposite
    @Override
    public String getId() {
        return this.id.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    // Méthodes d'agrégation
    public int getTotalVehicules() {
        int total = 0;
        // Ajouter les véhicules propres à cette compagnie
        // Pour l'exemple, supposons que chaque compagnie a 1 véhicule de base
        total += 1; // Ou récupérer depuis une autre source
        for (Company child : children) {
            total += child.getTotalVehicules();
        }
        return total;
    }

    public double getTotalAmount() {
        double total = 0;
        for (Order order : orders) {
            total += order.getAmount();
        }
        for (Company child : children) {
            total += child.getTotalAmount();
        }
        return total;
    }

    public List<Order> getOrder() {
        List<Order> allOrders = new ArrayList<>(this.orders);
        for (Company child : children) {
            allOrders.addAll(child.getOrder());
        }
        return allOrders;
    }
}

