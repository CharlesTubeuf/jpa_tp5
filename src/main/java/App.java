import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import tpBanque.BO.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
		EntityManager em = emf.createEntityManager();

		Adresse adresse = new Adresse (120, "Etienne Dolet", 94140, "Alfortville");
		Banque banque = new Banque ("Boursorama");
		Compte courant = new Compte("6666",1555.89);
		LivretA livretA = new LivretA();
		livretA.setTaux(1.52);
		livretA.setNumero("5555");
		livretA.setSolde(1500);		
		
		Operation ope = new Operation(LocalDate.now(),20,"Uber");
		livretA.addOperation(ope);
		
		AssuranceVie assuranceVie = new AssuranceVie (LocalDate.of(2050, 01, 23),1.30);
		assuranceVie.setNumero("123");
		assuranceVie.setSolde(300);
		
		Virement virement = new Virement("Charles");
		virement.setCompte(courant);
		virement.setDate(LocalDate.now());
		virement.setMontant(155);
		virement.setMotif("Casque Audio");
		
		Client charles = new Client("Tubeuf", "Charles", LocalDate.of(1995, 01, 23) );
		charles.setAdresse(adresse);
		charles.setBanque(banque);
		charles.addCompte(courant);
		charles.addCompte(livretA);
		charles.addCompte(assuranceVie);
		
		Client sega = new Client("Sylla", "Séga", LocalDate.of(1990, 8, 10));
		sega.setAdresse(adresse);
		sega.setBanque(banque);
		sega.addCompte(courant);
		
		// Compte courant associé à deux client
		
		em.getTransaction().begin();
		em.persist(charles);
		em.persist(sega);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		
	}

}
