package beans;

import jpaPackage.AutorEntity;
import jpaPackage.KsiazkaEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@ManagedBean(eager = true)
@SessionScoped
public class autorManagedBean {

    private List<AutorEntity> autorzy;
    private AutorEntity autorToAction;
    private boolean renderAddNewAuthor;
    private boolean renderUpdateNewAuthor;

    private String imie;
    private String nazwisko;

    final Logger log = Logger.getLogger(autorManagedBean.class.getName());

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.library.jpa");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public autorManagedBean() {
        renderAddNewAuthor = false;
        renderUpdateNewAuthor= false;
        imie = "";
        nazwisko = "";
        entityManager.getTransaction().begin();
        javax.persistence.Query q = entityManager.createQuery("SELECT a FROM AutorEntity a", AutorEntity.class);
        this.autorzy = q.getResultList();
        entityManager.getTransaction().commit();

    }

    public void addAuthor(){

        AutorEntity tmp = new AutorEntity(this.imie,this.nazwisko);
        entityManager.getTransaction().begin();
        entityManager.persist(tmp);
        entityManager.getTransaction().commit();
        this.autorzy = entityManager.createQuery("SELECT a FROM AutorEntity a", AutorEntity.class).getResultList();
        this.renderAddNewAuthor = false;
    }

    public void deleteAuthor(int id){
        try {
            entityManager.getTransaction().begin();
            log.info("usuwanie ");

            int result = entityManager.createQuery("DELETE FROM AutorEntity WHERE id =" + String.valueOf(id)).executeUpdate();
            entityManager.getTransaction().commit();
            log.info("Rows affected: " + result);
            this.autorzy = entityManager.createQuery("SELECT z FROM AutorEntity z", AutorEntity.class).getResultList();
        }
        catch(Exception e) {
            log.info("Blad przy usuwaniu rekordu: " + e);
        }
    }

    public void updateAuhtor(){

        try {

            entityManager.getTransaction().begin();

            log.info("aktualizowanie ");

            String hql = "UPDATE AutorEntity set imie = :imiee, nazwisko = :nazwiskoo WHERE idAutor = :id";
            Query query = entityManager.createQuery(hql);
            query.setParameter("id", this.autorToAction.getIdAutor());
            query.setParameter("imiee", imie);
            query.setParameter("nazwiskoo", nazwisko);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            entityManager.getTransaction().commit();
            entityManager.clear();
            this.autorzy.clear();
            this.autorzy = entityManager.createQuery("SELECT z FROM AutorEntity z", AutorEntity.class).getResultList();
            this.renderUpdateNewAuthor = false;
        }
        catch(Exception e) {
            log.info("Blad przy aktualizowaniu rekordu: " + e);
        }
    }


    public List<AutorEntity> getAutorzy() {
        return autorzy;
    }

    public void setAutorzy(List<AutorEntity> autorzy) {
        this.autorzy = autorzy;
    }

    public boolean isRenderAddNewAuthor() {
        return renderAddNewAuthor;
    }

    public void setRenderAddNewAuthor(boolean renderAddNewAuthor) {
        this.renderAddNewAuthor = renderAddNewAuthor;
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

    public boolean isRenderUpdateNewAuthor() {
        return renderUpdateNewAuthor;
    }

    public void setRenderUpdateNewAuthor(boolean renderUpdateNewAuthor) {
        this.renderUpdateNewAuthor = renderUpdateNewAuthor;
    }

    public AutorEntity getAutorToAction() {
        return autorToAction;
    }

    public void setAutorToAction(AutorEntity autorToAction) {
        this.renderUpdateNewAuthor = true;
        this.autorToAction = autorToAction;
        this.imie = autorToAction.getImie();
        this.nazwisko = autorToAction.getNazwisko();
    }

}
