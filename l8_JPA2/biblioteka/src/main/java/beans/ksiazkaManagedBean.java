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
public class ksiazkaManagedBean {

    private List<KsiazkaEntity> ksiazki;
    private boolean renderBooksAll = true;
    private AutorEntity particularAuthor;

    private KsiazkaEntity bookToAction;
    private boolean renderAddNewBook;
    private boolean renderUpdateBook;


    private String tytul;




    final Logger log = Logger.getLogger(ksiazkaManagedBean.class.getName());
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.library.jpa");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public ksiazkaManagedBean() {

        entityManager.getTransaction().begin();
        javax.persistence.Query q = entityManager.createQuery("SELECT a FROM KsiazkaEntity a", KsiazkaEntity.class);
        this.ksiazki = q.getResultList();
        entityManager.getTransaction().commit();

    }

    public void addBook(){

        KsiazkaEntity tmp = new KsiazkaEntity(this.tytul,this.particularAuthor);
        entityManager.getTransaction().begin();

       // entityManager.flush();
       // entityManager.clear();
        entityManager.persist(tmp);
        entityManager.getTransaction().commit();

        this.ksiazki = entityManager.createQuery("SELECT a FROM KsiazkaEntity a", KsiazkaEntity.class).getResultList();
        this.renderAddNewBook = false;
        log.info("AfterADD");
    }

    public void deleteBook(int id){
        try {
            entityManager.getTransaction().begin();
            log.info("usuwanie ");

            int result = entityManager.createQuery("DELETE FROM KsiazkaEntity WHERE id =" + String.valueOf(id)).executeUpdate();
            entityManager.getTransaction().commit();
            log.info("Rows affected: " + result);
            this.ksiazki = entityManager.createQuery("SELECT z FROM KsiazkaEntity z", KsiazkaEntity.class).getResultList();
        }
        catch(Exception e) {
            log.info("Blad przy usuwaniu rekordu: " + e);
        }
    }

    public void updateBook(){

        try {

            entityManager.getTransaction().begin();

            log.info("aktualizowanie ");

            String hql = "UPDATE KsiazkaEntity set tytul = :tytull, autor = :autorr WHERE idKsiazka = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", this.bookToAction.getIdKsiazka());
            query.setParameter("tytull", tytul);
            query.setParameter("autorr", particularAuthor);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            entityManager.getTransaction().commit();
            entityManager.clear();
            this.ksiazki.clear();
            this.ksiazki = entityManager.createQuery("SELECT z FROM KsiazkaEntity z", KsiazkaEntity.class).getResultList();
            this.renderUpdateBook = false;
        }
        catch(Exception e) {
            log.info("Blad przy aktualizowaniu rekordu: " + e);
        }
    }


    public List<KsiazkaEntity> getKsiazki() {
        return ksiazki;
    }

    public void setKsiazki(List<KsiazkaEntity> ksiazki) {
        this.ksiazki = ksiazki;
    }


    public void renderParticularBooks(AutorEntity autorEntity) {
        this.renderBooksAll = false;
        this.particularAuthor = autorEntity;
    }

    public boolean isRenderBooksAll() {
        return renderBooksAll;
    }

    public void setRenderBooksAll(boolean renderBooksAll) {
        this.renderBooksAll = renderBooksAll;
    }

    public AutorEntity getParticularAuthor() {
        return particularAuthor;
    }

    public void setParticularAuthor(AutorEntity particularAuthor) {
        this.particularAuthor = particularAuthor;
    }

    public KsiazkaEntity getBookToAction() {
        return bookToAction;
    }

    public void setBookToAction(KsiazkaEntity bookToAction) {
        this.bookToAction = bookToAction;
        this.tytul = bookToAction.getTytul();
        this.renderUpdateBook = true;
    }

    public boolean isRenderAddNewBook() {
        return renderAddNewBook;
    }

    public void setRenderAddNewBook(boolean renderAddNewBook) {
        this.renderAddNewBook = renderAddNewBook;
    }

    public boolean isRenderUpdateBook() {
        return renderUpdateBook;
    }

    public void setRenderUpdateBook(boolean renderUpdateBook) {
        this.renderUpdateBook = renderUpdateBook;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
}