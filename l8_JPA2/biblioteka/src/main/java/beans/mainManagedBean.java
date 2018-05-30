package beans;

import jpaPackage.AutorEntity;
import jpaPackage.CzytelnikEntity;
import jpaPackage.KsiazkaEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(eager = true)
@SessionScoped
public class mainManagedBean implements Serializable{

    final Logger log = Logger.getLogger(mainManagedBean.class.getName());

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.library.jpa");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    private String mainAction;
    private boolean afterChosenAction;

    List<CzytelnikEntity> resultFrom1;
    List<CzytelnikEntity> resultFrom2;
    List<AutorEntity> resultFrom3;

    List<KsiazkaEntity> resultFrom4;
    List<AutorEntity> resultFrom4Autor;

    public mainManagedBean() {
        this.mainAction = "";
        this.afterChosenAction = false;
    }

    public String actionTaken(String action){
        this.afterChosenAction = true;
        this.mainAction = action;
        return "index.xhtml";
    }

    public void proceed1(){

        entityManager.getTransaction().begin();

        javax.persistence.Query q = entityManager.createQuery(
                "SELECT a FROM CzytelnikEntity a JOIN" +
                        " WypozyczenieEntity w ON (a=w.czytelnik) WHERE w.ksiazka.autor.imie = 'ImieAAA' and " +
                        "w.dataWypozyczenia > '2015-09-25' and w.dataZwrotu < '2018-05-11'", CzytelnikEntity.class);


        resultFrom1 = q.getResultList();

        entityManager.getTransaction().commit();

    }

    public void proceed2(){

        entityManager.getTransaction().begin();

        javax.persistence.Query q = entityManager.createQuery(
                "SELECT a FROM CzytelnikEntity a JOIN" +
                " WypozyczenieEntity w ON (a=w.czytelnik) WHERE w.ksiazka.tytul = 'Tytul1' and " +
                "w.dataWypozyczenia > '2017-09-25' and w.dataZwrotu < '2018-01-11'", CzytelnikEntity.class);


        resultFrom2 = q.getResultList();

        entityManager.getTransaction().commit();

    }
    public void proceed3(){

        entityManager.getTransaction().begin();

        javax.persistence.Query q = entityManager.createQuery(
                "SELECT a FROM AutorEntity a WHERE a IN" +
                        "(SELECT w.ksiazka.autor FROM CzytelnikEntity a JOIN" +
                        " WypozyczenieEntity w ON (a=w.czytelnik) WHERE w.czytelnik.imie = 'ImieGGG')", AutorEntity.class);


        resultFrom3 = q.getResultList();

        entityManager.getTransaction().commit();

    }
    public void proceed4(){

        entityManager.getTransaction().begin();

        javax.persistence.Query q = entityManager.createQuery("SELECT k FROM KsiazkaEntity k JOIN WypozyczenieEntity w ON (k=w.ksiazka)", KsiazkaEntity.class);
        resultFrom4 = q.getResultList();
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        javax.persistence.Query q2 = entityManager.createQuery("SELECT k.autor FROM KsiazkaEntity k JOIN WypozyczenieEntity w ON (k=w.ksiazka) GROUP BY 1", AutorEntity.class);
        q2.setFirstResult(0);
        q2.setMaxResults(1);
        resultFrom4Autor = q2.getResultList();
        entityManager.getTransaction().commit();


        /*
        entityManager.getTransaction().begin();
        javax.persistence.Query q2 = entityManager.createQuery("SELECT au FROM AutorEntity au WHERE au in (" +
                "SELECT k.autor, COUNT(k) as amount FROM KsiazkaEntity k JOIN WypozyczenieEntity w ON (k=w.ksiazka) GROUP BY 1 )");

        q2.setFirstResult(0);
        q2.setMaxResults(1);

        resultFrom4Autor = q2.getResultList();

        entityManager.getTransaction().commit();
        */

    }


    public boolean isAfterChosenAction() {
        return afterChosenAction;
    }

    public void setAfterChosenAction(boolean afterChosenAction) {
        this.afterChosenAction = afterChosenAction;
    }

    public String getMainAction() {
        return mainAction;
    }

    public void setMainAction(String mainAction) {
        this.mainAction = mainAction;
    }

    public List<CzytelnikEntity> getResultFrom2() {
        return resultFrom2;
    }

    public void setResultFrom2(List<CzytelnikEntity> resultFrom2) {
        this.resultFrom2 = resultFrom2;
    }

    public List<KsiazkaEntity> getResultFrom4() {
        return resultFrom4;
    }

    public void setResultFrom4(List<KsiazkaEntity> resultFrom4) {
        this.resultFrom4 = resultFrom4;
    }

    public List<AutorEntity> getResultFrom4Autor() {
        return resultFrom4Autor;
    }

    public void setResultFrom4Autor(List<AutorEntity> resultFrom4Autor) {
        this.resultFrom4Autor = resultFrom4Autor;
    }

    public List<CzytelnikEntity> getResultFrom1() {
        return resultFrom1;
    }

    public void setResultFrom1(List<CzytelnikEntity> resultFrom1) {
        this.resultFrom1 = resultFrom1;
    }

    public List<AutorEntity> getResultFrom3() {
        return resultFrom3;
    }

    public void setResultFrom3(List<AutorEntity> resultFrom3) {
        this.resultFrom3 = resultFrom3;
    }
}
