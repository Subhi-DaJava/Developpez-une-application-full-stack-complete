# Projet-Full-Stack Monde de Dév
Cette application représente une solution Full Stack dédiée au réseau de développeurs (MDD). 
Elle est développée en utilisant Angular pour le frontend et Spring Boot pour le backend.

## Backend
Le backend est développé en utilisant Spring Boot version `3.2.2` et Java `21`.

### Prérequis
- `Java 21`
- `Maven 3.3+` (Dernière version recommandée)

### Configuration de variables d'environnement
Avant de démarrer l'application, vous devez définir les variables d'environnement suivantes sur votre système :

- `mysql_database_name` : Le nom de votre base de données MySQL.
- `user_name` : Le nom d'utilisateur pour accéder à votre base de données MySQL.
- `user_password` : Le mot de passe pour accéder à votre base de données MySQL.
- `MySecretKEY` : La clé secrète utilisée pour signer et vérifier les tokens JWT.

### Démarrage
#### Vous avez plusieurs options pour démarrer l'application :

##### 1. Exécutez la commande suivante pour démarrer l'application Spring Boot sous le répertoire `back` :
```bash 
mvn spring-boot:run
```

##### 2. Générez un fichier JAR exécutable, exécutez la commande suivante sous le répertoire `back`:

```bash 
mvn clean install 
```

##### Ensuite, exécutez le fichier `JAR` généré sous le répertoire `back` :

```bash 
java -jar target/mdd-api-0.0.1-SNAPSHOT.jar 
```

##### 3. Dans votre IDE préféré (IntelliJ IDEA, par exemple), en ajoutant les variables d'environnement nécessaires dans `Edit Configuration`, puis rajoutez cette configuration : 

```bash 
mysql_database_name={database_name};
user_name={mysql_username};
user_password={mysql_user_passord};
jwtKey={SecretKEY}
```` 
##### Remplacez les valeurs appropriées dans les accolades, puis démarrer l'application.

## Peupler la base de données

Pour peupler la base de données, exécutez le script SQL fourni dans le répertoire `src/main/resources/sql-script/data.sql` avec MySQL Workbench ou avec la commande `mysql -u root -p < data.sql` (Remplacez root par votre nom d'utilisateur MySQL et entrez votre mote de passe MySQL).

## Frontend

Cette application a été créée en utilisant Angular CLI (version `14.1.3`).

### Prérequis 

Avant de commencer, assurez-vous d'avoir `Node.js` (version plus récente) installé sur votre machine, pour installer les dépendances nécessaires, exécutez la commande `npm install`.

### Démarrage

Pour démarrer le serveur de développement, utilisez la commande `ng serve` ou `npm run start` sous le répertoire `front`, ensuite, ouvrez votre navigateur et accédez à http://localhost:4200.

## Génération de la Javadoc

Pour générer la Javadoc pour votre projet, exécutez la commande suivante sous le dossier `back`:

```bash 
mvn javadoc:javadoc 
```
La documentation Javadoc sera créée dans le répertoire `target/site/apidocs/`.

#### N.B. : `JwtSecretKEY` doit être une chaîne aléatoire de 256 bits. 