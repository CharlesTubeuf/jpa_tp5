package tpBanque.BO;

import jdk.jfr.Enabled;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LIVRET_A")
public class LivretA extends Compte {

    @Column(name = "TAUX", nullable = false)
    private double taux;

    public LivretA() {
    }

    public LivretA(double taux) {
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "LivretA{" +
                "taux=" + taux +
                '}';
    }
}
