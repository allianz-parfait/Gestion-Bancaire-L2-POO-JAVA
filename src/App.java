import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import models.Cheque;
import models.Client;
import models.Compte;
import models.Epargne;
import services.CompteService;

public class App {

    private static Scanner sc = new Scanner(System.in);
    private static CompteService compteService = new CompteService();

    public static void main(String[] args) throws Exception {

        int choix;

        do {
            choix = menu();
            
            switch (choix) {
                case 1:
                    Client client = new Client();
                    sc.nextLine(); // Quand on saisit un entier suivi d'une chaine, on doit mettre ca pour éviter les problèmes tampons
                    System.out.println("Entrer le nom du Client : ");
                    client.setNom(sc.nextLine());
                    System.out.println("Entrer le prénom du Client : ");
                    client.setPrenom(sc.nextLine());
                    compteService.ajouterClient(client);
                    break;

                case 2:
                    ArrayList<Client>clients = compteService.listerClients();
                    for (Client cl : clients) {
                        System.out.println(cl); // Mettre 'cl' == cl.toString() car derrière 'cl' fera appel à toString
                    }
                    break;

                case 3:
                    // Avant meme de créer un compte, on doit rechercher l'id du client. 
                    // S'il existe on lui crée un compte, sinon on ne crée rien (car c'est impossible d'avoir un compte sans client)
                    int idClient;
                    System.out.println("Entrez l'id du Client : ");
                    idClient = sc.nextInt();
                    client = compteService.searchClientById(idClient);
                    if (client == null) {
                        System.out.println("Ce client n'existe pas");
                    }
                    else {
                        // Comme ici on a héritage, on doit d'abord saisir les attributs de la classe mère puis ensuite demander laquelle des classes filles à créer (Cheque ou Epargne)
                        // Le nombre de choix sera équivalent au nombre de classes filles qu'on aura
                        String numero;
                        double solde;
                        sc.nextLine();
                        System.out.println("Entrer le numero du Compte : ");
                        numero = sc.nextLine();
                        System.out.println("Entrer le solde du Compte : ");
                        solde = sc.nextDouble();
                        int typeInt; // Le type du compte
                        do {
                            System.out.println("Choisir le Type de Compte : ");
                            System.out.println("1 - Cheque");
                            System.out.println("2 - Epargne");
                            typeInt = sc.nextInt();
                            sc.nextLine(); // Flush
                        } while (typeInt != 1 && typeInt != 2);

                        if (typeInt == 1) { // Si c'est égale à 1, alors on veut créer un compte de chèque
                            int frais;
                            System.out.println("Entrez les frais : ");
                            frais = sc.nextInt();
                            Cheque compteCheque = new Cheque(numero, solde, frais);
                            // Ici la conversion est faite automatiquement du fait de l'héritage. Une liste de comptes peut avoir des Comptes Cheque et Epargne 
                            compteService.addCompte(compteCheque);

                            // Une fois le compte créé, il faut maintenant créer la relation entre le client et le compte
                            // NB : La relation est Bidirectionnelle. Elle peut donc etre créé dans l'un des deux sens.

                            // Client vers Compte
                            client.addCompte(compteCheque);

                            // Compte vers Client
                            // compteCheque.setClient(client);
                        }
                        else {
                            String dateDB, dateFin;
                            System.out.println("Entrez la date de début de blocage (jj-mm-aaaa) : ");
                            dateDB = sc.nextLine();
                            System.out.println("Entrez la date de fin de blocage (jj-mm-aaaa) : ");
                            dateFin = sc.nextLine();
                            // Conversion des dates (string) en des localDate
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // On crée le format de notre localDate
                            
                            try { // Si le formattage ci-dessus se passe bien, on crée et enregistre le compte
                                Epargne compteEpargne = new Epargne(numero, solde, LocalDate.parse(dateDB, formatter), LocalDate.parse(dateFin, formatter));
                                // Ajout du Compte Epargne dans la liste des Comptes
                                compteService.addCompte(compteEpargne);

                                // Une fois le compte créé, il faut maintenant créer la relation entre le client et le compte
                                // NB : La relation est Bidirectionnelle. Elle peut donc etre créé dans l'un des deux sens.

                                // Client vers Compte
                                client.addCompte(compteEpargne);

                            // Compte vers Client
                            // compteEpargne.setClient(client);

                                // NB : On peut mettre autant de catch que l'on veut car on peut avoir différents cas d'exception
                            } catch (DateTimeParseException e) { // Si l'exception est de type erreur de formattage de la date donnée, on affiche le message suivant
                                // Sinon ici (catch), on affiche un message d'erreur
                                System.out.println("Erreur de conversion : vérifier le format de la date!");
                            }
                            catch (DateTimeException e) {
                                System.out.println("Date Invalide!");
                            }
                        }                        
                    }
                    break;

                case 4:
                    System.out.println("Entrer l'id du Client : ");
                    idClient = sc.nextInt();
                    client = compteService.searchClientById(idClient);
                    if (client == null) {
                        System.out.println("Ce Client n'existe pas!");
                    }
                    else {
                        ArrayList<Compte> comptes = compteService.getComptesByClient(client);
                        if (comptes == null) throw new RuntimeException("Ce client n'a aucun compte!") ;
                        for (Compte compte : comptes) {                            
                            System.out.println(compte.consultation());
                        }
                    }
                    break;
            
                default:
                    System.out.println("Ce choix n'existe pas!");
                    break;
            }
        } while (choix != 8);
    }

    public static int menu() {
        int choix;
        System.out.println("\n1 - Ajouter Client");
        System.out.println("2 - Lister Clients");
        System.out.println("3 - Créer Compte");
        System.out.println("4 - Lister les Comptes d'un client");
        System.out.println("5 - Faire une transaction");
        System.out.println("6 - Lister les transactions d'un compte");
        System.out.println("7 - Lister les transactions d'un client");
        System.out.println("8 - Quitter");
        System.out.println("\nFaites votre choix : ");

        choix = sc.nextInt();

        return choix;
    }
}
