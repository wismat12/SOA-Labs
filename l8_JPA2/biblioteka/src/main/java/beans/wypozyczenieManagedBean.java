package beans;

import jpaPackage.AutorEntity;
import jpaPackage.CzytelnikEntity;
import jpaPackage.KsiazkaEntity;
import jpaPackage.WypozyczenieEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(eager = true)
@SessionScoped
public class wypozyczenieManagedBean {

    private List<WypozyczenieEntity> wypozyczenia;
    private List<KsiazkaEntity> availableBooks;

    private boolean renderWypAll = true;
    private WypozyczenieEntity wypozyczenieToAction;

    private KsiazkaEntity particularBook;
    private CzytelnikEntity particularReader;

    private boolean renderAddNewWypozyczenie;
    private boolean renderUpdateWypozyczenie;

    private boolean validDate = false;



    private Date dataWypozyczenia;
    private Date dataZwrotu;







    final Logger log = Logger.getLogger(wypozyczenieManagedBean.class.getName());

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.library.jpa");

    EntityManager entityManager = entityManagerFactory.createEntityManager();



    public wypozyczenieManagedBean() {

        entityManager.getTransaction().begin();
        javax.persistence.Query q = entityManager.createQuery("SELECT a FROM WypozyczenieEntity a", WypozyczenieEntity.class);
        this.wypozyczenia = q.getResultList();
        entityManager.getTransaction().commit();
        log.info("PRZED DOSTEPNE");
        prepareAvailableBooks();

    }


    public void prepareAvailableBooks(){
/*
        for(WypozyczenieEntity w : this.wypozyczenia){
            if(w.getDataZwrotu() == null){

            }
        }

        entityManager.getTransaction().begin();

        log.info("dostepne ksiazki");


        String hql = "SELECT a from KsiazkaEntity a WHERE a in " +
                "(SELECT b from KsiazkaEntity b JOIN WypozyczenieEntity w ON b.idKsiazka = w.ksiazka.idKsiazka WHERE w.dataZwrotu is not null)";
        Query query = entityManager.createQuery(hql);

        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);

        entityManager.getTransaction().commit();
        entityManager.clear();
        this.availableBooks.clear();
        this.availableBooks = entityManager.createQuery("SELECT z FROM KsiazkaEntity z", KsiazkaEntity.class).getResultList();
*/
    }

    public void addWyp(){

        WypozyczenieEntity tmp = new WypozyczenieEntity(new java.sql.Date(new Date().getTime()),null,this.particularReader,this.particularBook);
        entityManager.getTransaction().begin();

        // entityManager.flush();
        // entityManager.clear();
        entityManager.persist(tmp);
        entityManager.getTransaction().commit();

        this.wypozyczenia = entityManager.createQuery("SELECT a FROM WypozyczenieEntity a", WypozyczenieEntity.class).getResultList();
        this.renderAddNewWypozyczenie = false;
        log.info("AfterADD");
        prepareAvailableBooks();
    }

    public void deleteWyp(int id){
        try {
            entityManager.getTransaction().begin();
            log.info("usuwanie ");

            int result = entityManager.createQuery("DELETE FROM WypozyczenieEntity WHERE id =" + String.valueOf(id)).executeUpdate();
            entityManager.getTransaction().commit();
            log.info("Rows affected: " + result);
            this.wypozyczenia = entityManager.createQuery("SELECT z FROM WypozyczenieEntity z", WypozyczenieEntity.class).getResultList();
            prepareAvailableBooks();
        }
        catch(Exception e) {
            log.info("Blad przy usuwaniu rekordu: " + e);
        }
    }

    public void markAsReturned(int id){

        try {

            entityManager.getTransaction().begin();

            log.info("markAsReturned ");

            String hql = "UPDATE WypozyczenieEntity set dataZwrotu = :to WHERE idWypozyczenie = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("to", new java.sql.Date(new Date().getTime()));
            //query.setParameter("czyt", this.particularReader);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            entityManager.getTransaction().commit();
            entityManager.clear();
            this.wypozyczenia.clear();
            this.wypozyczenia = entityManager.createQuery("SELECT z FROM WypozyczenieEntity z", WypozyczenieEntity.class).getResultList();
            this.renderUpdateWypozyczenie = false;
            prepareAvailableBooks();
        }
        catch(Exception e) {
            log.info("Blad przy aktualizowaniu rekordu: " + e);
        }
    }

    public List<WypozyczenieEntity> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<WypozyczenieEntity> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }

    public boolean isRenderWypAll() {
        return renderWypAll;
    }

    public void setRenderWypAll(boolean renderWypAll) {
        this.renderWypAll = renderWypAll;
    }

    public WypozyczenieEntity getWypozyczenieToAction() {
        return wypozyczenieToAction;
    }

    public void setWypozyczenieToAction(WypozyczenieEntity wypozyczenieToAction) {
        this.wypozyczenieToAction = wypozyczenieToAction;
    }

    public KsiazkaEntity getParticularBook() {
        return particularBook;
    }

    public void setParticularBook(KsiazkaEntity particularBook) {
        this.particularBook = particularBook;
    }

    public CzytelnikEntity getParticularReader() {
        return particularReader;
    }

    public void setParticularReader(CzytelnikEntity particularReader) {
        this.particularReader = particularReader;
    }

    public boolean isRenderAddNewWypozyczenie() {
        return renderAddNewWypozyczenie;
    }

    public void setRenderAddNewWypozyczenie(boolean renderAddNewWypozyczenie) {
        this.renderAddNewWypozyczenie = renderAddNewWypozyczenie;
    }

    public boolean isRenderUpdateWypozyczenie() {
        return renderUpdateWypozyczenie;
    }

    public void setRenderUpdateWypozyczenie(boolean renderUpdateWypozyczenie) {
        this.renderUpdateWypozyczenie = renderUpdateWypozyczenie;
    }

    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public Date getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(Date dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public boolean isValidDate() {
        return validDate;
    }

    public void setValidDate(boolean validDate) {
        this.validDate = validDate;
    }
}