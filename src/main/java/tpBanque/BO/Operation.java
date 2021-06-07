package tpBanque.BO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "MONTANT", nullable = false)
    private double montant;

    @Column(name = "MOTIF", nullable = false)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "ID_COMPTE")
    private Compte compte;

    public Operation() {
    }

    public Operation(long id, LocalDate date, double montant, String motif) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.motif = motif;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        // Si cette operation était déjà lié à un compte
        // -> Suppression de l'operation dans la liste du compte associé
        if (this.compte != null){
            this.compte.getOperations().remove(this);
        }

        //Changement de compte associé
        this.compte = compte;

        //Ajout de l'operation dans la liste du compte associé
        if (compte != null){
            compte.getOperations().add(this);
        }
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", date=" + date +
                ", montant=" + montant +
                ", motif='" + motif + '\'' +
                ", compte=" + compte +
                '}';
    }
}
