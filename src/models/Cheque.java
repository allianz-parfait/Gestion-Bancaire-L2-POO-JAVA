package models;

import models.enums.TypeCompte;

public class Cheque extends Compte { // Cheque hérite de Compte
    
    private int frais;

    public int getFrais() {
        return frais;
    }

    public void setFrais(int frais) {
        this.frais = frais;
        // Pour le type il ya 3 options : Vu que Cheque hérite des méthodes et attributs de Compte, on peut faire
        // this.type, super.type ou type.
        type = TypeCompte.Cheque; // A la création d'un objet compte, on lui donne le type Cheque
    }

    public Cheque() {
        // En créant un Cheque, on crée un Compte, donc on doit mettre le mot clé super qui signifie le parent (Compte)
        super();
    }
    
    public Cheque(String numero, double solde, int frais) {
        super(numero, solde);
        this.frais = frais;
    }

    @Override
    public String consultation() { // On redéfinit la méthode consultation
        // TODO Auto-generated method stub
        return super.consultation() + ", Frais : " + frais;
    }

    @Override
    public boolean depot(double montant) { // On redéfinit la méthode depot
        // TODO Auto-generated method stub
        return super.depot(montant-frais); // On soustrait à chaque dépot, les frais sur le montant
    }

}
