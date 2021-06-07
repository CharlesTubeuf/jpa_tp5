import tpBanque.BO.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.time.LocalDate;

public class TestBanque {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("banque");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Creation d'un client puis insertion en base de donnée avec liaisons
//        entityManager.getTransaction().begin();
//        Adresse adresse = new Adresse(2, "rue" , 44850, "Nantes");
//        Client client = new Client("test", "sega", LocalDate.now());
//        client.setAdresse(adresse);
//        client.addCompte(new Compte("123", 12.5));
//
//        Banque banque = new Banque("laposte");
//        client.setBanque(banque);
//
//        entityManager.persist(client);
//        entityManager.getTransaction().commit();

        //Creation d'une banque puis insertion en base de donnée avec liaisons
        entityManager.getTransaction().begin();
        Banque banque = new Banque("toto");

        Adresse adresse = new Adresse(2, "rue" , 44850, "Nantes");
        Client client = new Client("test", "sega", LocalDate.now());
        client.setAdresse(adresse);
        client.addCompte(new Compte("123", 12.5));

        banque.addClient(client);

        entityManager.persist(banque);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
