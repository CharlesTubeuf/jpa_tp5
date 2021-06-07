package fr.diginamic.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "LIVRE")
public class Livre implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID" )
    private int id;

    @Column( name = "TITRE", nullable = false )
    private String titre;

    @Column( name = "AUTEUR", length = 50, nullable = false )
    private String auteur;

    @ManyToMany
    @JoinTable( name = "COMPO" ,
            joinColumns = @JoinColumn( name = "ID_LIV", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn( name = "ID_EMP", referencedColumnName = "ID"))
    private Set<Emprunt> emprunts;


    public Livre() {
        this.emprunts = new HashSet();
    }

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
        this.emprunts = new HashSet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }


    public void addEmprunt(Emprunt emprunt){
        this.emprunts.add(emprunt);
    }

    public void removeEmprunt(Emprunt emprunt){
        this.emprunts.remove(emprunt);
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}
