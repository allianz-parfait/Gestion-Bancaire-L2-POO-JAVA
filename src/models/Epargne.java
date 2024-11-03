package models;

import java.time.LocalDate;

public class Epargne extends Compte {
    
    private static double taux = 0.05;
    private LocalDate dateDbBlocage;
    private LocalDate dateFinBlocage;

    public Epargne() {
    }

    public Epargne(String numero, double solde, LocalDate dateDbBlocage, LocalDate dateFinBlocage) {
        super(numero, solde);
        this.dateDbBlocage = dateDbBlocage;
        this.dateFinBlocage = dateFinBlocage;
    }

    public static double getTaux() {
        return taux;
    }

    public static void setTaux(double taux) {
        Epargne.taux = taux;
    }

    public LocalDate getDateDbBlocage() {
        return dateDbBlocage;
    }

    public void setDateDbBlocage(LocalDate dateDbBlocage) {
        this.dateDbBlocage = dateDbBlocage;
    }

    public LocalDate getDateFinBlocage() {
        return dateFinBlocage;
    }

    public void setDateFinBlocage(LocalDate dateFinBlocage) {
        this.dateFinBlocage = dateFinBlocage;
    }

    @Override
    public String consultation() {
        // TODO Auto-generated method stub
        String ch = super.consultation() + ", Taux" + taux;
        if (!transactionIsPossible()) {
            ch += " Bloqué du " + dateDbBlocage + " au " + dateFinBlocage;
        }
        return ch;
    }

    @Override
    public boolean Virement(double montant, Compte Cptedepot) {
        // TODO Auto-generated method stub
        if (transactionIsPossible()) {            
            return super.Virement(montant, Cptedepot);
        }
        return false;
    }

    @Override
    public boolean depot(double montant) { // Un dépot n'est possible qui si l'on est dans la période de début de blocage
        // TODO Auto-generated method stub
        if (transactionIsPossible()) { // Si la transaction est possible, on fait l'opération            
            return super.depot(montant);
        }
        return false;
    }

    @Override
    public boolean retrait(double montant) {
        // TODO Auto-generated method stub
        if (transactionIsPossible()) {
            return super.retrait(montant);
        }
        return false;
    }
    
    // Supposons que la date de blocage est du 01-07-24 au 30-07-2024
    // 24-07-24 ==== les opérations sont impossible vu qu'on est dans la période de blocage
    // 30-06-24 ==== les opérations sont possible vu qu'on n'est pas dans la période de blocage
    private boolean transactionIsPossible() {
        /*
            Les transactions (dépot, retrait et virement) ne sont possibles que si la date actuelle
            n'est pas compris entre l'intervalle de la dateDbBlocage et la dateFinBlocage.
        */
        LocalDate now = LocalDate.now(); // La date du jour
        if (now.isAfter(dateDbBlocage) && now.isBefore(dateFinBlocage)) {
            return false; // C'est Impossible
        }
        return true; // C'est possible
    }
    
}
