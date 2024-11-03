# Gestion-Bancaire-POO-JAVA

## Description

Le projet **Gestion-Bancaire-POO-JAVA** est une application console développée en Java dans le cadre d'un travail pratique (TP) de licence 2. Ce TP aborde des concepts avancés de programmation orientée objet (POO) tels que l'héritage, le polymorphisme, le downcasting, l'upcasting, les attributs et méthodes statiques, ainsi que les classes abstraites. Ce programme permet de gérer différents types de comptes bancaires, incluant des comptes chèques et d'épargne.

## Concepts Utilisés

- **Héritage** : Les classes `Cheque` et `Epargne` héritent de la classe `Compte`, permettant de partager des caractéristiques communes et d'ajouter des fonctionnalités spécifiques à chaque type de compte.
- **Polymorphisme** : Utilisé pour traiter les objets des classes `Cheque` et `Epargne` comme des instances de la classe parent `Compte`, simplifiant ainsi la gestion des opérations sur différents types de comptes.
- **Downcasting et Upcasting** : Gère les références entre les classes parent et enfant, permettant des manipulations dynamiques des objets.
- **Attributs et Méthodes Statiques** : Les attributs et méthodes statiques appartiennent à la classe plutôt qu'à une instance spécifique de la classe. Cela signifie qu'ils peuvent être appelés sans créer d'objets de cette classe. Les attributs statiques sont souvent utilisés pour stocker des constantes ou des compteurs partagés.
- **Classes Abstraites** : Les classes abstraites sont des classes qui ne peuvent pas être instanciées directement et qui peuvent contenir des méthodes abstraites (sans implémentation) que les sous-classes doivent implémenter. Elles sont utilisées pour définir une base commune tout en forçant les classes filles à fournir des implémentations spécifiques.

### Classe `final`
Une classe est dite `final` lorsqu'elle ne peut pas être étendue, c'est-à-dire qu'elle ne peut avoir aucune classe fille. Cela est utile pour empêcher toute modification des comportements d'une classe déjà définie.

### Gestion de l'Héritage lors de la Saisie
Pour gérer l'héritage dans la saisie des données, il est recommandé de suivre cette approche :
1. Commencez par saisir les attributs communs à toutes les classes (par exemple, le numéro de compte et le solde pour la classe `Compte`).
2. Ensuite, demandez à l'utilisateur quel type de compte il souhaite créer, en précisant s'il s'agit d'un compte de chèque ou d'un compte d'épargne.

### Conversion d'Objets dans l'Héritage
Dans le cadre de l'héritage, un objet d'une classe fille peut être converti automatiquement en un objet de la classe mère. En revanche, pour convertir un objet d'une classe mère en un objet d'une classe fille, un **downcasting** est nécessaire. Cette conversion doit être effectuée explicitement, car elle peut entraîner des erreurs si l'objet n'est pas effectivement une instance de la classe fille.

## Modèles

- **Client** : Représente un client bancaire avec des informations personnelles et des comptes associés.
- **Compte** : Classe de base pour les comptes bancaires, contenant des attributs et méthodes communs.
- **Cheque** : Hérite de `Compte`, représentant un compte courant avec des fonctionnalités spécifiques.
- **Epargne** : Hérite de `Compte`, représentant un compte d'épargne avec des caractéristiques distinctes.

## Services

- **CompteService** : Classe contenant les fonctionnalités principales pour la gestion des comptes, y compris :
  - **Dépôt** : Ajouter des fonds à un compte.
  - **Virement** : Transférer des fonds d'un compte à un autre.
  - **Création de compte** : Créer des comptes chèques et d'épargne, avec des opérations spécifiques en fonction du type de compte.

## Installation

1. Cloner le dépôt :

   ```bash
   git clone https://github.com/votre-utilisateur/Gestion-Bancaire-L2-POO-JAVA.git
