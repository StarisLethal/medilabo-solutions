markdown

# MEDILABO SOLUTIONS

## Prérequis
- Git
- Docker
- Docker Compose
- MongoDB Compass

## Installation

### Cloner le dépôt sur votre machine :
```bash
git clone https://github.com/StarisLethal/medilabo-solutions.git
cd medilabo-solutions
```
### Récupérer l'image la plus à jour de MongoDB :

`
docker pull mongo:latest
`
Build les images de chacun des microservices

Pour ce faire, vous rendre dans chaque dossier de microservice et utiliser docker build -t en respectant les noms d'image du docker-compose ou en l'adaptant.
Exemple:

`
docker build -t ms-authserv:1 .
`
### Lancer l'ensemble des services

Se rendre à la racine de medilabo-solutions puis :

`
docker-compose up -d
`

### Préparer les bases de données

#### Microservice patients

1. Ouvrez une session MongoDB pour le service `patients`:
    ```bash
    docker exec -it mongodb mongosh "mongodb://root:example@localhost:27017/"
    ```

2. Sélectionnez la base de données appropriée :
    ```bash
    use db_patients
    ```

3. Créez un utilisateur avec les droits nécessaires :
    ```bash
    db.createUser({
        user: "admin",
        pwd: "1234",
        roles: [
            { role: "dbAdmin", db: "db_patients" },
            { role: "readWrite", db: "db_patients" }
        ]
    })
    ```

#### Microservice patientNote

1. Ouvrez une session MongoDB pour le service `patientNote`:
    ```bash
    docker exec -it mongodb_note mongosh "mongodb://root:example@localhost:27027/"
    ```

2. Sélectionnez la base de données appropriée :
    ```bash
    use db_patientNote
    ```

3. Créez un utilisateur avec les droits nécessaires :
    ```bash
    db.createUser({
        user: "admin_note",
        pwd: "1234",
        roles: [
            { role: "dbAdmin", db: "db_patientNote" },
            { role: "readWrite", db: "db_patientNote" }
        ]
    })
    ```

#### Microservice authserv

1. Ouvrez une session MongoDB pour le service `authserv`:
    ```bash
    docker exec -it mongodb_user mongosh "mongodb://root:example@localhost:27037/"
    ```

2. Sélectionnez la base de données appropriée :
    ```bash
    use db_user
    ```

3. Créez un utilisateur avec les droits nécessaires :
    ```bash
    db.createUser({
        user: "admin_user",
        pwd: "1234",
        roles: [
            { role: "dbAdmin", db: "db_user" },
            { role: "readWrite", db: "db_user" }
        ]
    })
    ```

### Importer les bases de données de test
Utiliser MongoDB Compass pour importer les bases de données de test qui se trouvent dans le dossier `config_db`.

Exemple BDD user:
1. Dans New Connection, remplir le champ URI: `mongodb://admin_user:1234@localhost:27037/db_user`
2. Sélectionner la database `db_user`.
3. Sélectionner la collection `db_user`.
4. Sélectionner ADD DATA puis `import JSON` et sélectionner le json `db_user.db_user.json`.

### Identifiant et mot de passe Front-end
```
identifiant : test
password : 123456
```

## Suggestions d'actions GREEN CODING

### Hebergement

Utiliser un hébergeur qui s'alimente en énergie verte et qui met l'accent sur la réduction des émissions de carbone de son infrastructure.
### Optimisation du code

Refactoriser et optimiser le code au maximum. Par exemple : 

```java
    public Boolean diabeteEarlyOnSet(String birthDate, String gender, UUID id) {
        Integer symptom = symptomCount(id);
        Integer age = getAge(birthDate);
        if ((gender.equals("M") && age < 30 && symptom > 5) || (gender.equals("F") && age < 30 && symptom > 7) || (age >= 30 && symptom > 8)) {
            System.out.println(symptom);
            return true;
        }
        return false;
    }
```

pourrait devenir :

```java
public boolean diabeteEarlyOnSet(String birthDate, String gender, UUID id) {
    int age = getAge(birthDate);
    if (age < 30) {
        int symptomThreshold = gender.equals("M") ? 5 : 7;
        return symptomCount(id) > symptomThreshold;
    } else {
        return symptomCount(id) > 8;
    }
}
```

### Front-end Léger

Pour améliorer l'efficacité énergétique de notre front-end, il est crucial de maintenir une interface utilisateur simple. Cela permet de minimiser les transferts de données.