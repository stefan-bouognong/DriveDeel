package com.drivedreal.drivedreal.entity;

import com.drivedreal.drivedreal.domain.composite.CompanyComposite;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "subsidiaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subsidiary implements CompanyComposite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubsidiary;
    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company; // Référence à la compagnie mère

    @Override
    public String getId() {
        return this.idSubsidiary.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }
}
