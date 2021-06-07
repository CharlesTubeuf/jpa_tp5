package tpBanque.BO;

import tpBanque.ListService;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NOM", length = 50, nullable = false)
    private String nom;

    @Column(name = "PRENOM", length = 50, nullable = false)
    private String prenom;

    @Column(name = "DATE_NAISSANCE", nullable = false)
    private LocalDate dateNaissance;

    @Embedded
    private Adresse adresse;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_BANQUE", nullable = false)
    private Banque banque;

    @ManyToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinTable(name = "CLIENT_COMPTE",
            joinColumns = @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_COMPTE", referencedColumnName = "ID"))
    private Set<Compte> comptes;

    {
        comptes = new HashSet<>();
    }

    public Client() {
    }

    public Client(String nom, String prenom, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Banque getBanque() {
        return banque;
    }

    public void setBanque(Banque banque) {
        // Si ce client était déjà lié à une banque
        // -> Suppression du client dans la liste de la banque associé
        if (this.banque != null){
            this.banque.getClients().remove(this);
        }

        //Changement de banque associé
        this.banque = banque;

        //Ajout du client dans la liste de la banque associé
        if (banque != null) {
            banque.getClients().add(this);
        }
    }

    public Set<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(Set<Compte> comptes) {
        if (comptes == null){
            return;
        }
        this.comptes = comptes;
    }

    public void addCompte(Compte compte) {
        if (compte == null) {
            return;
        }

        // this.addCompte se fait en caché lors de l'ajout du client au compte en question
        compte.addClient(this);
    }

    public void removeCompte(Compte compte) throws Exception {
        if (compte == null){
            return;
        }
        //Le retrait du compte de la liste de nos comptes se fait dans la methode
        // de retrait de client du compte en question
        compte.removeClient(this);
    }

    public void checkIfRemovePermitOnComptes(Set<Compte> list) throws Exception {
        if (ListService.isNullAfterRemove(list)){
            throw new Exception("comptes can't be empty");
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", adresse=" + adresse +
                ", banque=" + banque +
                ", comptes=" + comptes +
                '}';
    }
}
