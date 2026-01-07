package com.drivedreal.drivedreal;

import jakarta.persistence.*;
@Entity
@Table(name = "vehicles")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String reference;
    private String vehicles_Type;
    private String base_price;
    private String description;
    private String stock_entry_date;
    private String stock_status;

    public String getType(){
        return this.vehicles_Type;
    }

    public Float getPrice(){
        return Float.parseFloat(this.base_price);
    }

}
