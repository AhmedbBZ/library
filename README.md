# Gestion d'une Bibliothèque

Cette application de gestion de bibliothèque a été développée en Java en respectant une architecture en couches (présentation, contrôle, service et persistance). Elle permet de gérer les livres, les clients, les abonnements, les emprunts et d'autres fonctionnalités essentielles à une bibliothèque moderne.

## Fonctionnalités

### Gestion des médias
- **Ajout, modification, suppression, affichage (liste et individuel)** des livres, magazines et DVD.
- Champs gérés : 
  - Titre
  - Auteur(s)
  - Année
  - ISBN
  - Description
  - Type
  - Nombre d'exemplaires
  - Nombre de pages
  - Éditeur
- **Recherche** : Rechercher un média par mot-clé et vérifier sa disponibilité (soit "disponible", soit "quand il sera disponible").

### Gestion des clients
- **Ajout, modification, suppression, affichage** des clients.
- Champs gérés :
  - Nom
  - Prénom
  - CIN
  - Date de naissance
  - Adresse
  - Numéro de téléphone

### Gestion des abonnements
- Création et renouvellement d'abonnements.
- Tarif : 5D par mois.

### Gestion des emprunts
- Les clients peuvent emprunter :
  - Jusqu'à **3 entités** pour une durée de **2 semaines**.
  - Les clients premium peuvent emprunter **5 entités**.
- Notifications automatiques envoyées aux clients qui doivent rendre leurs emprunts dans les **24 heures**.

### Gestion des comptes et de la comptabilité
- Suivi des paiements d'abonnement.
- Gestion des pénalités pour les retards.

### Gestion de la liste noire
- Identification des clients qui n'ont pas rendu leurs emprunts.
- Application de pénalités pour les retards prolongés.

## Technologies utilisées
- **Langage** : Java
- **Stockage** : Fichiers JSON pour la persistance des données.
- **Architecture** : Projet structuré en plusieurs couches :
  - **Présentation** : Interface utilisateur et interactions.
  - **Contrôle** : Gestion des flux et des règles métiers.
  - **Service** : Logique applicative et traitements.
  - **Persistance** : Gestion des fichiers JSON.

## Prérequis
- Java 8 ou supérieur.
- Un IDE ou un éditeur de texte pour exécuter l'application.

## Installation et exécution
1. Clonez le dépôt ou téléchargez les fichiers du projet.
2. Assurez-vous que Java est installé sur votre système.
3. Importez le projet dans votre IDE préféré.
4. Pour assurer le fonctionnement du projet sur votre ordinateur, accédez à **Java Build Path** et ajoutez le fichier `json-simple-1.1.1.jar` en fonction de l'emplacement de votre projet.
5. Exécutez l'application en lançant la classe principale (`Main`).

## Exemple de structure des fichiers JSON

### Exemple d'un fichier de stockage pour les livres :
```json
[
  {
    "titre": "Le Petit Prince",
    "auteurs": ["Antoine de Saint-Exupéry"],
    "annee": 1943,
    "ISBN": "123456789",
    "description": "Un conte poétique et philosophique.",
    "type": "Livre",
    "nombreExemplaires": 5,
    "pages": 96,
    "editeur": "Gallimard"
  }
]
```

### Exemple d'un fichier de stockage pour les clients :
```json
[
  {
    "nom": "Doe",
    "prenom": "John",
    "cin": "01234567",
    "dateNaissance": "1990-05-15",
    "adresse": "123 Rue Principale",
    "numeroTelephone": "98765432"
  }
]
```

## Gestion des exceptions
- Validation des données (par exemple, format des dates, numéros de téléphone, etc.).
- Gestion des erreurs liées au fichier JSON (lecture, écriture, fichiers manquants).
- Contrôle des règles métier (nombre maximum d'emprunts, retards, etc.).

## Notes importantes
- **Règles métier respectées** : Toutes les contraintes liées à la gestion d'une bibliothèque (comme le nombre maximum d'emprunts et la gestion des retards) sont implémentées.
- **Notifications** : Le système notifie les clients concernés automatiquement (via une simulation ou un affichage).

## Améliorations possibles
- Implémentation d'une interface graphique (GUI) pour améliorer l'expérience utilisateur.
- Migration vers une base de données relationnelle ou NoSQL pour un stockage plus robuste.
- Ajout d'une gestion multi-utilisateurs pour différents niveaux d'accès (administrateurs, bibliothécaires, etc.).

