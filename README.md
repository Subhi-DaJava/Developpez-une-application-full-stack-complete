# Full-Stack-Projet-Monde-de-Dév

Cette application représente une solution Full Stack dédiée au réseau de développeurs (MDD). 
Elle est développée en utilisant Angular pour le frontend et Spring Boot pour le backend.

## Backend
Le backend est développé en utilisant Spring Boot version 3.2.2 et Java 21.

### Prérequis
- Java 21
- Maven 3.3+ (Dernière version recommandée)

### Configuration de variables d'environnement
Remplacez les variables d'environnement suivant dans le fichier application.properties :
```bash 
spring.datasource.username=${user_name}
spring.datasource.password=${user_password} 
jwtKey=${MySecretKEY}
 ```
### Démarrage
#### Choix 1
Démarrer l'application
Exécutez la commande suivante pour démarrer l'application Spring Boot :

```bash 
mvn spring-boot:run 
```
#### Choix 2
Générer un fichier JAR exécutable
Pour générer un fichier JAR exécutable, exécutez la commande suivante :

```bash 
mvn clean package 
```
Ensuite, exécutez le fichier JAR généré :

```bash 
java -jar target/mdd-api-0.0.1-SNAPSHOT.jar 
```
## Peupler la base de données
Pour peupler la base de données, exécutez le script SQL fourni dans le répertoire `src/main/resources/sql-script/data.sql` avec MySQL Workbench ou avec la commande `mysql -u root -p < data.sql` (Remplacez root par votre nom d'utilisateur MySQL et entrez votre mote de passe MySQL).

## Frontend
Cette application a été créée en utilisant Angular CLI (version 14.1.3).

### Prérequis 
Avant de commencer, assurez-vous d'avoir Node.js (version plus récente) installé sur votre machine.
Pour installer les dépendances nécessaires, exécutez la commande `npm install`.

### Démarrage
Pour démarrer le serveur de développement, utilisez la commande `ng serve` ou `npm run start`.
Ensuite, ouvrez votre navigateur et accédez à http://localhost:4200.

## Génération de la Javadoc
Pour générer la Javadoc pour votre projet, exécutez la commande suivante :

```bash 
mvn javadoc:javadoc 
```
La documentation Javadoc sera créée dans le répertoire target/site/apidocs/.
