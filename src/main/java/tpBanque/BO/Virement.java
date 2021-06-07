package tpBanque.BO;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Virement")
public class Virement extends Operation {

    @Column(name = "BENEFICIAIRE", nullable = false)
    private String beneficiaire;

    public Virement() {
    }

    public Virement(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    public String getBeneficiaire() {
        return beneficiaire;
    }

    public void setBeneficiaire(String beneficiaire) {
        this.beneficiaire = beneficiaire;
    }

    @Override
    public String toString() {
        return "Virement{" +
                "beneficiaire='" + beneficiaire + '\'' +
                '}';
    }
}
