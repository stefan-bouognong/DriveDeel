package com.drivedreal.drivedreal.entity;

import com.drivedreal.drivedreal.domain.observer.CatalogueObservable;
import com.drivedreal.drivedreal.domain.observer.CatalogueObserver;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "catalogues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalogue implements CatalogueObservable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "catalogue", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<VehicleEntity> vehicles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "catalogue_subscribers",
            joinColumns = @JoinColumn(name = "catalogue_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> subscribedUsers = new HashSet<>();

    // #region agent log
    private void logDebug(String message, Object data, String hypothesisId) {
        try (FileWriter fw = new FileWriter("c:\\Users\\LaVue\\Desktop\\dev\\java\\DriveDeel\\.cursor\\debug.log", true)) {
            fw.write(String.format("{\"sessionId\":\"debug-session\",\"runId\":\"run1\",\"hypothesisId\":\"%s\",\"location\":\"Catalogue.java:%d\",\"message\":\"%s\",\"data\":%s,\"timestamp\":%d}\n",
                     hypothesisId, new Throwable().getStackTrace()[1].getLineNumber(), message, data != null ? data.toString() : "null", System.currentTimeMillis()));
        } catch (IOException e) { /* ignore */ }
    }
    // #endregion

    @Override
    public void addObserver(CatalogueObserver observer) {
        if (observer instanceof User) {
            subscribedUsers.add((User) observer);
            logDebug("Observer added to catalogue: " + ((User)observer).getEmail(), null, "F");
        }
    }

    @Override
    public void removeObserver(CatalogueObserver observer) {
        if (observer instanceof User) {
            subscribedUsers.remove((User) observer);
            logDebug("Observer removed from catalogue: " + ((User)observer).getEmail(), null, "F");
        }
    }

    @Override
    public void notifyObservers(String message) {
        logDebug("Notifying observers about catalogue update: " + message, null, "F");
        for (User user : subscribedUsers) {
            user.update(message);
        }
    }

    public void addVehicle(VehicleEntity vehicle) {
        this.vehicles.add(vehicle);
        // Pas de notification directe ici, le service s'en chargera
    }
}
