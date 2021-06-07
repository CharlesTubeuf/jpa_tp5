package fr.diginamic.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "EMPRUNT" )
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "ID")
    private int id;

    @Column( name = "DATE_DEBUT" )
    private LocalDate dateDebut;

    @Column( name = "DELAI" )
    private int delai;

    @Column( name = "DATE_FIN" )
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn( name = "ID_CLIENT" )
    private Client client;

    @ManyToMany( mappedBy = "emprunts" )
    private Set<Livre> livres;


    public Emprunt() {
        this.livres = new HashSet();
    }

    public Emprunt(LocalDate dateDebut, int delai, LocalDate dateFin) {
        this.dateDebut = dateDebut;
        this.delai = delai;
        this.dateFin = dateFin;
        this.livres = new HashSet();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (this.client != null){
            this.client.getEmprunts().remove(this);
        }
        this.client = client;

        if (client != null){
            client.getEmprunts().add(this);
        }
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    public void addLivre(Livre livre){
        this.livres.add(livre);
    }

    public void removeLivre(Livre livre){
        this.livres.remove(livre);
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", delai=" + delai +
                ", dateFin=" + dateFin +
                ", client=" + client.getId() +
                '}';
    }
}
