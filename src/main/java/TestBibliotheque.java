import fr.diginamic.bo.Client;
import fr.diginamic.bo.Emprunt;
import fr.diginamic.bo.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class TestBibliotheque {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test_jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Emprunt emprunt = entityManager.find(Emprunt.class, 2);

        Set<Livre> livres = emprunt.getLivres();
        List<Emprunt> emprunts = findAllEmpruntByClientId(entityManager, 1);

        System.out.println(emprunt);
        //System.out.println(livres.iterator().next());
        System.out.println(emprunts.get(0));




        //Création d'un nouvel emprunt avec un livre
        entityManager.getTransaction().begin();

        Emprunt emprunt1 = new Emprunt();
        emprunt1.addLivre(livres.iterator().next());
        emprunt1.setDateDebut(LocalDate.now());

        Client client = new Client("testCascade", "testCascade");

        //client.addEmprunt(emprunt1);
        emprunt1.setClient(null);
        entityManager.persist(client);
        //entityManager.persist(emprunt1);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    private static List<Emprunt> findAllEmpruntByClientId(EntityManager em, int id){
        return em.createQuery(String.format("SELECT e FROM Emprunt e WHERE ID_CLIENT=%d", id), Emprunt.class).getResultList();
    }
}