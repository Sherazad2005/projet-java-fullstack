# Projet Java Fullstack – Spring Boot & React

##  Présation

Ce projet est une application **fullstack** développée avec **Spring Boot** pour le backend et **React** pour le frontend.  
Il s'agit d'un mini site **e-commerce** permettant la gestion des utilisateurs, des produits et des commandes, avec une sécurisation par **JWT**.

---

##  Architecture du projet

projet-java-fullstack

│

├── backend (Spring Boot)

│ ├── controller

│ ├── model

│ ├── repository

│ ├── security

│ ├── dto

│ └── config

│

├── react-app (Frontend React)

│ ├── src

│ │ ├── pages

│ │ ├── components

│ │ ├── auth

│ │ └── api

│ └── main.jsx

│

└── bdd

└── database.sql


yaml
Copier le code

 **Le script SQL complet de la base de données est fourni dans le dossier `bdd/database.sql`**

---

##  Technologies utilisées

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT (Json Web Token)
- Spring Data JPA
- MySQL
- Maven

### Frontend
- React
- Vite
- Axios
- React Router DOM

---

##  Installation du projet

### 1️ Base de données

Créer la base MySQL :

```sql
CREATE DATABASE backoffice_app;
Configurer application.properties :

properties
Copier le code
spring.datasource.url=jdbc:mysql://localhost:3306/backoffice_app
spring.datasource.username=root
spring.datasource.password=
Importer ensuite le fichier SQL :

pgsql
Copier le code
bdd/database.sql
2️ Lancer le backend
bash
Copier le code
cd projet-java-fullstack
mvn spring-boot:run
Backend disponible sur :
http://localhost:8081

3 Lancer le frontend
bash
Copier le code
cd react-app
npm install
npm run dev
Frontend disponible sur :
 http://localhost:5173

Comptes de test
Rôle	Login	Mot de passe
ADMIN	admin	admin
USER	shera	shera

Fonctionnalités
Utilisateur
Inscription

Connexion

Consultation des produits

Passage de commande

Consultation de ses commandes

Administrateur
Gestion des produits (ajout / suppression)

Gestion des utilisateurs

Changement de rôles (USER / ADMIN)

Accès sécurisé aux routes admin

Vidéo de démonstration
 https://youtu.be/PXh_ltVwzao

La vidéo montre :

Le lancement du projet

Les fonctionnalités utilisateur

Les fonctionnalités administrateur

Les appels API

Les tests en conditions réelles

Tests réalisés
Les tests ont été effectués via :

L’interface React

Le navigateur (Console & Network)

La base de données (phpMyAdmin)

La vidéo de démonstration

Sécurité
Authentification par JWT

Mots de passe hashés (BCrypt)

Routes protégées par rôles

CORS configuré pour React

Accès admin restreint

Auteur
Sherazad Abdallah
Projet Java Fullstack – Sup de Vinci
