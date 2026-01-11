# DriveDeal – Pattern Command (Gestion des ventes de véhicules)

## Objectif

Ce module illustre l'utilisation du design pattern Command dans une application Spring Boot. Il permet d'appliquer des promotions sur des véhicules en stock de manière encapsulée, traçable et réversible (undo/redo).

## Pattern utilisé

| Rôle | Classe/Interface | Pattern Command |
|------|-----------------|-----------------|
| Command | `ICommand` | Interface abstraite |
| ConcreteCommand | `SaleVehicleCommand` | Commande concrète |
| Receiver | `SaleManager` | Exécuteur réel |
| Invoker | `CommandInvoker` | Gestionnaire d'historique |
| Client | `SaleController` | Client utilisant les commandes |
| Exposition | API REST via `SaleController` | |

## Prérequis

- Java 17+
- Maven
- MySQL (base `drive_dreal`)
- Postman (ou navigateur web)

## Structure des fichiers

```
src/main/java/com/drivedreal/drivedreal
│
├── domain/
│   └── vehicle/
│       └── Vehicle.java                    # Entity (Receiver)
│
├── services/
│   ├── command/
│   │   ├── ICommand.java                   # Interface Command
│   │   └── SaleVehicleCommand.java         # ConcreteCommand
│   ├── invoker/
│   │   └── CommandInvoker.java             # Invoker
│   └── manager/
│       └── SaleManager.java                # Receiver
│
├── controllers/
│   └── SaleController.java                 # API REST
│
└── repository/
    └── VehicleRepository.java
```

## Lancer l'application

À la racine du projet DriveDeal :

```bash
mvn clean install
mvn spring-boot:run
```

L'application démarre sur http://localhost:8080



## Tester l'application

### Appliquer une promotion sur les véhicules

GET http://localhost:8080/api/sales/apply

Effet :
- Création d'un `SaleVehicleCommand` par véhicule
- Application d'une remise de 20%
- Mise à jour du statut à `ON_SALE`
- Commande enregistrée dans l'historique


### Annuler la dernière vente (Undo)

GET http://localhost:8080/api/sales/undo

Effet :
- Annulation de la dernière commande exécutée
- Restauration du prix original
- Retour du statut à `IN_STOCK`

### Lister les véhicules

GET http://localhost:8080/api/sales/list

Résultat :
- Affiche l'état courant des véhicules (prix et statut)
exemple:
    [
        {
            "idVehicle": "V001",
            "price": 8000.0,
            "stockStatus": "ON_SALE"
        },
        {
            "idVehicle": "V002",
            "price": 9600.0,
            "stockStatus": "ON_SALE"
        },
        {
            "idVehicle": "V003",
            "price": 9000.0,
            "stockStatus": "IN_STOCK"
        }
    ]


Lorsque tous les tests sont bien lancés dans la console s'affiche :

Executed: SaleVehicleCommand[vehicle=V001, discount=20,00%]
Executed: SaleVehicleCommand[vehicle=V002, discount=20,00%]
Executed: SaleVehicleCommand[vehicle=V003, discount=20,00%]
Undone: SaleVehicleCommand[vehicle=V003, discount=20,00%]