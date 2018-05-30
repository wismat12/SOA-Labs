import jpaPackage.ZawodnikEntity;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Main {
    /*
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
*/
    public static void main(final String[] args) throws Exception {

        final Logger log = Logger.getLogger(Main.class.getName());

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.tutorial.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ZawodnikEntity zawodnik = new ZawodnikEntity();

        zawodnik.setId(20);
        zawodnik.setImie("AAA");
        zawodnik.setNazwisko("AAA2");
        zawodnik.setPunkty(2000);
        zawodnik.setNarodowosc("POL");

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(zawodnik);
            entityManager.getTransaction().commit();



            //log.info("Dodano " + zawodnik.toString());

            Query q = entityManager.createQuery("SELECT z FROM ZawodnikEntity z", ZawodnikEntity.class);
            List<ZawodnikEntity> zawodnicy = q.getResultList();
            for (ZawodnikEntity s : zawodnicy)
                log.info(s.toString());


            entityManager.close();
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }



 /*
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
        */

    }
    /*
    <T> TypedQuery<T> jpaQuery(String query, Class<T> aclass){

    }
    */
}