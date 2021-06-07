import fr.diginamic.bo.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class TestJpa {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test_jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        Livre livre = em.find(Livre.class, 3);
        System.out.println(livre);

        em.getTransaction().begin();
        livre = new Livre("test", "Loïc");
        em.persist(livre);

        livre = em.find(Livre.class, 5);
        if (livre != null){
            livre.setTitre("Du plaisir dans la cuisine");
        }
        em.getTransaction().commit();

        System.out.println(selectLivreByTitre(em, "test"));
        System.out.println(selectLivreByAuteur(em, "Loïc").get(0));

        em.getTransaction().begin();
        livre = selectLivreByTitre(em, "test");
        if (livre != null){
            em.remove(livre);
        }
        em.getTransaction().commit();

        System.out.println(em.createQuery("SELECT l FROM Livre l", Livre.class).getResultList().get(0));

        em.close();
        entityManagerFactory.close();
    }

    public static Livre selectLivreByTitre(EntityManager em, String titre){
        TypedQuery<Livre> resultQuery = em.createQuery(
                String.format("SELECT l FROM Livre l WHERE l.titre = '%s'", titre),
                Livre.class);

        return resultQuery.getSingleResult();
    }

    public static List<Livre> selectLivreByAuteur(EntityManager em, String auteur){
        TypedQuery<Livre> resultQuery = em.createQuery(
                String.format("SELECT l FROM Livre l WHERE l.auteur = '%s'", auteur),
                Livre.class);

        return resultQuery.getResultList();
    }
}
