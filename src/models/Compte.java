package models;

import models.enums.TypeCompte;

// Compte est une classe abstraite =====> classe non instanciable.
// On ne pourra pas créer de compte mais des comptes de chèque et des comptes d'épargne.

// Compte est une classe final =====> Interdit d'avoir des classes filles.
// NB : Lorsqu'on a héritage (en UML), il faut créer un attribut Type qui aura la valeur de chèque ou d'épargne
public abstract class Compte {
    // Un attribut statique est un attribut partagé à l'ensemble des instances et accessible 
    // à travers la classe.
    
    // Attribut Statique
    private static int nbreCompte; // Attributs numériques initialisés par défaut à 0.

    // Attributs d'instances
    protected int id; // L'id sera auto-incrémenté
    protected String numero; // Le numero du compte sera généré
    protected double solde;
    protected TypeCompte type; // Le type du compte (Cheque ou Epargne)

    // Attributs Navigationnels
    Client client;
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // Cette méthode permettra de générer le numéro. On le met en private pour ne pas qu'il soit visible au niveau de l'interface.
    // Utiliser une classe Java pour le faire
    // id = 1 ====> numero ====>   CPT00001
    // id = 2 ====> numero ====>   CPT00002
    // id = 10 ====> numero ====>  CPT00010
    // id = 100 ====> numero ====> CPT00100
    /*private String generateNumero() {
        return null;
    }*/

    public Compte() {
        id = ++nbreCompte;
    }
    
    public Compte(double solde) {
        id = ++nbreCompte;
        this.solde = solde;
    }

    public Compte(String numero, double solde) {
        id = ++nbreCompte;
        this.numero = numero;
        this.solde = solde;
    }

    public static int getNbreCompte() {
        return nbreCompte;
    }

    public static void setNbreCompte(int nbreCompte) {
        Compte.nbreCompte = nbreCompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((numero == null) ? 0 : numero.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) { // Permettra de tester l'égalité de deux comptes à partir de leur numéro.
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Compte other = (Compte) obj;
        if (numero == null) {
            if (other.numero != null)
                return false;
        } else if (!numero.equals(other.numero))
            return false;
        return true;
    }

    // Retraits
    public boolean retrait(double montant) {
        if (montant < solde) {
            solde -= montant;
            return true;
        }
        return false;
    }

    // Depot
    public boolean depot(double montant) {
        solde += montant;
        return true;
    }

    // Virement
    public boolean Virement(double montant, Compte Cptedepot) {
        // Si le retrait du montant sur le compte en cours s'est bien passé, on affecte au compte de dépot le montant
        if (this.retrait(montant)) { // On fait d'abord un retrait sur le compte en cours
            return Cptedepot.depot(montant);
        }
        return false;
    }

    // Consultation
    public String consultation() {
        return "id=" + id + 
                ", numero=" + numero + 
                ", solde=" + solde + 
                ", type=" + type;
    }

}
