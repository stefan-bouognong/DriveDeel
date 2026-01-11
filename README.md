# DriveDeel – Pattern Adapter (Gestion des documents PDF)

## 🎯 Objectif
Ce module illustre l'utilisation du **design pattern Adapter** dans une application Spring Boot.  
Il permet de traiter de manière uniforme des documents HTML et PDF, même si le PDF provient d'une bibliothèque externe non compatible avec l'interface attendue.


## 🧱 Pattern utilisé

| Rôle | Classe/Interface |
|------|------------------|
| **Pattern** | Adapter |
| **Target** | `Document` (interface abstraite) |
| **Adaptee** | `PDFDocument` (bibliothèque PDF externe simulée) |
| **Adapter** | `PDFDocumentAdapter` |
| **Client** | `DocumentClient` |
| **Exposition** | API REST via `DocumentController` |



## ⚙️ Prérequis

- **Java 17+**
- **Maven**
- **Postman** (ou navigateur web)


## 🔧 Structure des fichiers

src/main/java/com/drivedeel/
├── model/
│   ├── Document.java              # Interface abstraite (Target)
│   ├── HTMLDocument.java          # Implémentation directe
│   ├── PDFDocument.java           # Bibliothèque externe (Adaptee)
│   └── PDFDocumentAdapter.java    # Adapter
├── client/
│   └── DocumentClient.java        # Client utilisant les documents
└── controller/
    └── DocumentController.java    # API REST




## ▶️ Lancer l'application

À la racine du projet **DriveDeel** :

```
mvn clean install
mvn spring-boot:run
```

L'application démarre sur **http://localhost:8080**



## 🧪 Tester l'application

### 🔹 Tester un document HTML
```
GET http://localhost:8080/api/documents/html
```

### 🔹 Tester un document PDF (Adapter)
```
GET http://localhost:8080/api/documents/pdf
```

