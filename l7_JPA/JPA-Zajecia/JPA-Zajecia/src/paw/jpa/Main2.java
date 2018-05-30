package paw.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main2 {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
		EntityManager em = factory.createEntityManager();
		
		try {
			Query q = em.createQuery("FROM Student", Student.class);
			List<Student> students = q.getResultList();
			for (Student s : students)
				System.out.println(s);
		}
		catch(Exception e) {
			System.err.println("Blad przy pobueraniu rekord�w: " + e);
		}

	}

}
