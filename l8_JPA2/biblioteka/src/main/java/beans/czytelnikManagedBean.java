package beans;

import jpaPackage.AutorEntity;
import jpaPackage.CzytelnikEntity;
import jpaPackage.KsiazkaEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(eager = true)
@SessionScoped
public class czytelnikManagedBean {

    private List<CzytelnikEntity> czytelnicy;

    private CzytelnikEntity readerToAction;
    private boolean renderAddNewReader;
    private boolean renderUpdateNewReader;

    private String imie;
    private String nazwisko;




    final Logger log = Logger.getLogger(czytelnikManagedBean.class.getName());

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.library.jpa");

    EntityManager entityManager = entityManagerFactory.createEntityManager();



    public czytelnikManagedBean() {

        entityManager.getTransaction().begin();
        javax.persistence.Query q = entityManager.createQuery("SELECT a FROM CzytelnikEntity a", CzytelnikEntity.class);
        this.czytelnicy = q.getResultList();
        entityManager.getTransaction().commit();

    }

    public void addReader(){

        CzytelnikEntity tmp = new CzytelnikEntity(this.imie,this.nazwisko);
        entityManager.getTransaction().begin();
        entityManager.persist(tmp);
        entityManager.getTransaction().commit();
        this.czytelnicy = entityManager.createQuery("SELECT a FROM CzytelnikEntity a", CzytelnikEntity.class).getResultList();
        this.renderAddNewReader = false;
    }

    public void deleteReader(int id){
        try {
            entityManager.getTransaction().begin();
            log.info("usuwanie ");

            int result = entityManager.createQuery("DELETE FROM CzytelnikEntity WHERE id =" + String.valueOf(id)).executeUpdate();
            entityManager.getTransaction().commit();
            log.info("Rows affected: " + result);
            this.czytelnicy = entityManager.createQuery("SELECT z FROM CzytelnikEntity z", CzytelnikEntity.class).getResultList();
        }
        catch(Exception e) {
            log.info("Blad przy usuwaniu rekordu: " + e);
        }
    }

    public void updateReader(){

        try {

            entityManager.getTransaction().begin();

            log.info("aktualizowanie ");

            String hql = "UPDATE CzytelnikEntity set imie = :imiee, nazwisko = :nazwiskoo WHERE idCzytelnik = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", this.readerToAction.getIdCzytelnik());
            query.setParameter("imiee", imie);
            query.setParameter("nazwiskoo", nazwisko);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            entityManager.getTransaction().commit();
            entityManager.clear();
            this.czytelnicy.clear();
            this.czytelnicy = entityManager.createQuery("SELECT z FROM CzytelnikEntity z", CzytelnikEntity.class).getResultList();
            this.renderUpdateNewReader = false;
        }
        catch(Exception e) {
            log.info("Blad przy aktualizowaniu rekordu: " + e);
        }
    }


    public List<CzytelnikEntity> getCzytelnicy() {
        return czytelnicy;
    }

    public void setCzytelnicy(List<CzytelnikEntity> czytelnicy) {
        this.czytelnicy = czytelnicy;
    }


    public CzytelnikEntity getReaderToAction() {
        return readerToAction;
    }

    public void setReaderToAction(CzytelnikEntity readerToAction) {
        this.readerToAction = readerToAction;
        this.renderUpdateNewReader = true;
    }

    public boolean isRenderAddNewReader() {
        return renderAddNewReader;
    }

    public void setRenderAddNewReader(boolean renderAddNewReader) {
        this.renderAddNewReader = renderAddNewReader;
    }

    public boolean isRenderUpdateNewReader() {
        return renderUpdateNewReader;
    }

    public void setRenderUpdateNewReader(boolean renderUpdateNewReader) {
        this.renderUpdateNewReader = renderUpdateNewReader;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}