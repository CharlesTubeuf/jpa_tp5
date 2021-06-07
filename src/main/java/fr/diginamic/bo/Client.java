package fr.diginamic.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "CLIENT" )
public class Client implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID")
    private int id;

    @Column( name = "NOM", length = 50)
    private String nom;

    @Column( name = "PRENOM", length = 50)
    private String prenom;

    @OneToMany( mappedBy = "client", cascade = CascadeType.PERSIST )
    private Set<Emprunt> emprunts;


    public Client() {
        this.emprunts = new HashSet();
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        this.emprunts = new HashSet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public void addEmprunt(Emprunt emprunt){
        if (emprunt != null){
            emprunt.setClient(this);
        }
    }

    public void removeEmprunt(Emprunt emprunt){
        this.emprunts.remove(emprunt);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", emprunts=" + emprunts +
                '}';
    }
}
