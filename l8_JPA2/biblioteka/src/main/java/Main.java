import jpaPackage.AutorEntity;
import jpaPackage.CzytelnikEntity;
import jpaPackage.KsiazkaEntity;
import jpaPackage.WypozyczenieEntity;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.EntityType;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

    public static void main(final String[] args) throws Exception {


        final Logger log = Logger.getLogger(Main.class.getName());

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.library.jpa");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        javax.persistence.Query q = entityManager.createQuery("SELECT a FROM CzytelnikEntity a JOIN WypozyczenieEntity w ON (a=w.czytelnik)", CzytelnikEntity.class);
        List<CzytelnikEntity> autorzy = q.getResultList();

        entityManager.getTransaction().commit();

        //For JPA Criteria
        //https://chlebik.wordpress.com/2014/03/12/dajcie-kawalek-sqla-zapytania-criteria-api/
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        //List<CzytelnikEntity> autorzyCriteriaJPAAPI = criteriaBuilder.createQuery(CzytelnikEntity.class)


        //For Hibernate session
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        Transaction tr = session.beginTransaction();
        //Native SQL
        String sql = "SELECT * FROM czytelnik;";
        List<CzytelnikEntity> autorzyFromNativeSql = session.createSQLQuery(sql).addEntity( CzytelnikEntity.class).list();
        List<CzytelnikEntity> autorzyCriteria = session.createCriteria(CzytelnikEntity.class).list();
        tr.commit();


        for (CzytelnikEntity s : autorzyCriteria) {
            log.info(s.toString());
           // for(KsiazkaEntity k: s.getKsiazki()){
               // log.info(k.toString());
           // }
        }


        entityManager.close();

    }
}