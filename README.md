# DriveDeel

## How to Launch the Application

1. **Prerequisites:**
    - Java 17 or higher installed.
    - MySQL running locally on port 3306 with a schema named `drive_dreal`.
    - Update `src/main/resources/application.properties` with your MySQL username and password.

2. **Build & Run:**
    - Using command line:
        ```bash
        ./mvnw spring-boot:run
        ```
    - Or in your IDE, run the main class in the Spring Boot application.

The application will start on: `http://localhost:8080/`

---

## Available REST Routes

All routes are prefixed with `/api/commandes`.

### 1. Get All Commandes

- **Endpoint:** `GET /api/commandes`
- **Description:** Returns a list of all commandes.
- **Request:** No body required.
- **Response:**  
  ```json
  [
    {
      "idCommande": 1,
      "numCommande": "CMD-...",
      "montantTotal": 12000000,
      "typeCommande": "credit"
    },
    ...
  ]
  ```

---

### 2. Get Commande by ID

- **Endpoint:** `GET /api/commandes/{id}`
- **Description:** Returns a commande with the specified ID.
- **Request:** No body required.
- **Response:**  
  ```json
  {
    "idCommande": 1,
    "numCommande": "CMD-...",
    "montantTotal": 12000000,
    "typeCommande": "credit"
  }
  ```

---

### 3. Create a Comptant Commande

- **Endpoint:** `POST /api/commandes/comptant`
- **Description:** Creates a new cash (comptant) commande.
- **Request Body:**  
  Just a number (raw JSON), representing the `montantTotal`, for example:
  ```
  5000000
  ```
- **Response:**  
  ```json
  {
    "idCommande": 2,
    "numCommande": "CMD-...",
    "montantTotal": 5000000,
    "typeCommande": "comptant"
  }
  ```

---

### 4. Create a Credit Commande

- **Endpoint:** `POST /api/commandes/credit`
- **Description:** Creates a new credit commande.
- **Request Body:**  
  Just a number (raw JSON), representing the `montantTotal`, for example:
  ```
  6000000
  ```
- **Response:**  
  ```json
  {
    "idCommande": 3,
    "numCommande": "CMD-...",
    "montantTotal": 6000000,
    "typeCommande": "credit"
  }
  ```

---

### 5. Delete a Commande

- **Endpoint:** `DELETE /api/commandes/{id}`
- **Description:** Deletes a commande with the specified ID.
- **Request:** No body required.

---

### 6. Cancel a Commande

- **Endpoint:** `POST /api/commandes/{id}/cancel`
- **Description:** Cancels the commande with the given ID.
- **Request:** No body required.
- **Response:**  
  ```json
  {
    "idCommande": 4,
    "numCommande": "CMD-...",
    "montantTotal": 5000000,
    "typeCommande": "comptant",
    "statut": "cancelled"
  }
  ```

---

### 7. Succeed (Approve) a Commande

- **Endpoint:** `POST /api/commandes/{id}/succeed`
- **Description:** Marks the commande with the given ID as succeeded/approved.
- **Request:** No body required.
- **Response:**  
  ```json
  {
    "idCommande": 4,
    "numCommande": "CMD-...",
    "montantTotal": 5000000,
    "typeCommande": "comptant",
    "statut": "succeed"
  }
  ```

---

### 8. Fail a Commande

- **Endpoint:** `POST /api/commandes/{id}/failed`
- **Description:** Marks the commande with the given ID as failed/rejected.
- **Request:** No body required.
- **Response:**  
  ```json
  {
    "idCommande": 4,
    "numCommande": "CMD-...",
    "montantTotal": 5000000,
    "typeCommande": "comptant",
    "statut": "failed"
  }
  ```

---


---

## Catalogue Endpoints

### 1. Ajouter un véhicule au catalogue

- **Endpoint:** `POST /api/catalogue/ajouter`
- **Description:** Ajoute un nouveau véhicule au catalogue.
- **Request Body:**  
  Exemple :
  ```json
  {
    "marque": "Toyota",
    "modele": "Corolla",
    "prix": 21000
  }
  ```
- **Response:**  
  Status 200 OK (pas de corps de réponse)

---

### 2. Lister les véhicules du catalogue

- **Endpoint:** `GET /api/catalogue/vehicules`
- **Description:** Récupère la liste de tous les véhicules disponibles dans le catalogue.
- **Response:**  
  ```json
  [
    {
      "marque": "Toyota",
      "modele": "Corolla",
      "prix": 21000
    },
    {
      "marque": "Honda",
      "modele": "Civic",
      "prix": 23000
    }
    // ... autres véhicules
  ]
  ```

---




## Notes

- When creating commandes, send the total amount as a raw number (not as JSON object).
- All responses are in JSON.
- Make sure your database is running and accessible.

Enjoy using **DriveDeel**!

