package models;

import java.util.ArrayList;

// Client est une classe concrète =====> produit des objets
public class Client {

    // Un attribut statique est un attribut partagé à l'ensemble des instances et accessible 
    // à travers la classe.
    
    // Attribut Statique
    private static int nbreClients; // Attributs numériques initialisés par défaut à 0.

    // Attributs d'instances
    private int id; // L'id sera auto-incrémenté
    private String nom, prenom;

    // Attributs Navigationnels
    // OneToMany
    ArrayList<Compte> comptes = new ArrayList<>();

    
    public ArrayList<Compte> getComptes() {
        return comptes;
    }

    public void addCompte(Compte compte) { // Méthode pour ajouter un Compte à la liste des Comptes
        comptes.add(compte);
    }

    public Client() { // Le constructeur sans arguments va nous permettre d'initialiser nos objets
        // A chaque création d'un objet client, on incrémente l'id de 1.
        this.id = ++nbreClients;
    }

    public Client(String nom, String prenom) {
        // A chaque création d'un objet client, on incrémente l'id de 1.
        this.id = ++nbreClients;
        this.nom = nom;
        this.prenom = prenom;
    }

    public static int getNbreClients() {
        return nbreClients;
    }

    public static void setNbreClients(int nbreClients) {
        Client.nbreClients = nbreClients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) { // Méthode qui va nous permettre de comparer deux objets à partir de leurs id
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id != other.id)
            return false;
        return true;
    }
}