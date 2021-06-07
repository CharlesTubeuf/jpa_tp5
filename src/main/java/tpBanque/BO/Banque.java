package tpBanque.BO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BANQUE")
public class Banque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NOM", length = 50, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "banque" , cascade = CascadeType.PERSIST)
    private Set<Client> clients;

    {
        clients = new HashSet<>();
    }

    public Banque() {
    }

    public Banque(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        if (client == null) {
            return;
        }
        // this.clients.add(client); -> se fait en cacher dans setBanque
        client.setBanque(this);
    }

    public void removeClient(Client client) throws Exception {
        if (client == null) {
            return;
        }

        client.checkIfRemovePermitOnComptes(client.getComptes());

        this.clients.remove(client);
        client.getComptes().remove(this);
    }

    @Override
    public String toString() {
        return "Banque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", clients=" + clients +
                '}';
    }
}
