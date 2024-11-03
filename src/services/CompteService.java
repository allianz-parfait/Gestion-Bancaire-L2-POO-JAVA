package services;

import java.util.ArrayList;

import models.Client;
import models.Compte;

public class CompteService {
    
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Compte> comptes = new ArrayList<>(); // Dans cette liste de comptes on pourra trouver des comptes de chèque et des comptes d'épargne du fait de l'héritage
    // ArrayList<Epargne> comptesEpargnes = new ArrayList<>();
    // Use case

    public void ajouterClient(Client client) { // Méthode pour ajouter un Client
        clients.add(client);
    }

    public ArrayList<Client> listerClients() { // Méthode pour lister les Clients
        return clients;
    }

    public Client searchClientById(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    public void addCompte(Compte compte) { // Méthode pour créer un Compte
        comptes.add(compte);
    }

    public ArrayList<Compte> getComptesByClient(Client client) { // Méthode pour lister les Comptes d'un Client
        return client.getComptes();
    }
}
