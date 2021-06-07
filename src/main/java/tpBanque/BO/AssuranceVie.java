package tpBanque.BO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ASSURANCE_VIE")
public class AssuranceVie extends Compte {

    @Column(name = "DATE_FIN", nullable = false)
    private LocalDate dateFin;

    @Column(name = "TAUX", nullable = false)
    private double taux;

    public AssuranceVie() {
    }

    public AssuranceVie(LocalDate dateFin, double taux) {
        this.dateFin = dateFin;
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "AssuranceVie{" +
                "dateFin=" + dateFin +
                ", taux=" + taux +
                '}';
    }
}
