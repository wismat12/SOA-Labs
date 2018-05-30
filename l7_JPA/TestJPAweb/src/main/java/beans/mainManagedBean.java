package beans;

import jpaPackage.SklepEntity;
import jpaPackage.ZawodnikEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(eager = true)
@SessionScoped
public class mainManagedBean implements Serializable{

    ArrayList<String> dbs;
    String chosenDb;
    String chosenAction;
    String idToUpdate;
    boolean actionsRendered;
    boolean afterChosenAction;
    ArrayList<String> recordsDb;
    ArrayList<String> actions;

    List<ZawodnikEntity> zawodnicy;
    List<SklepEntity> sklepy;

    List<String> chosenZawodnicy;

    final Logger log = Logger.getLogger(mainManagedBean.class.getName());

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pl.agh.hibernate.tutorial.jpa");

    EntityManager entityManager = entityManagerFactory.createEntityManager();

    private String id;
    private String imie;
    private String nazwisko;
    private String punkty;
    private String narodowosc;

    public mainManagedBean() {
        this.dbs = new ArrayList<>();
        this.recordsDb = new ArrayList<>();
        this.actions = new ArrayList<>();
        dbs.add("zawodnik");
        dbs.add("sklep");
        this.chosenDb = "";
        this.chosenAction = "";
        this.idToUpdate = "";
        this.actions.add("Dodaj rekord");
        this.actions.add("Usuń rekord");
        this.actions.add("Aktualizuj rekord");
        this.actionsRendered = false;
        afterChosenAction = false;

        try {
            entityManager.getTransaction().begin();

            this.zawodnicy = entityManager.createQuery("SELECT z FROM ZawodnikEntity z", ZawodnikEntity.class).getResultList();

            this.sklepy = entityManager.createQuery("SELECT s FROM SklepEntity s", SklepEntity.class).getResultList();
            entityManager.getTransaction().commit();
            //entityManager.close();

        }
        catch(Exception e) {
            log.info("Blad przy ladowaniu: " + e);
        }

    }

    public String getFormType(){
        if(this.chosenDb.equals("zawodnik")){
            if(this.chosenAction.equals("Dodaj rekord")){
                return "dodajZawodnik.xhtml";
            }
            if(this.chosenAction.equals("Usuń rekord")){
                return "usunZawodnik.xhtml";
            }
            if(this.chosenAction.equals("Aktualizuj rekord")){
                return "aktualizujZawodnik.xhtml";
            }
        }
        if(this.chosenDb.equals("sklep")){
            if(this.chosenAction.equals("Dodaj rekord")){
                return "dodajSklep.xhtml";
            }
            if(this.chosenAction.equals("Usuń rekord")){
                return "usunSklep.xhtml";
            }
            if(this.chosenAction.equals("Aktualizuj rekord")){
                return "aktualizujSklep.xhtml";
            }
        }
        return null;
    }

    public String addZawodnik(){

        ZawodnikEntity tmp = new ZawodnikEntity();
        tmp.setId(Integer.valueOf(this.id));
        tmp.setImie(this.imie);
        tmp.setNazwisko(this.nazwisko);
        tmp.setPunkty(Integer.valueOf(this.punkty));
        tmp.setNarodowosc(this.narodowosc);

            try {
                entityManager.getTransaction().begin();

                log.info("dodawanie ");
                //entityManager.createQuery("DELETE FROM ZawodnikEntity z WHERE z.id = :idd").setParameter("idd",Integer.parseInt(zToDel));
                entityManager.persist(tmp);
                entityManager.getTransaction().commit();

                this.zawodnicy = entityManager.createQuery("SELECT z FROM ZawodnikEntity z", ZawodnikEntity.class).getResultList();

                // entityManager.close();

            }
            catch(Exception e) {
                log.info("Blad przy dodawaniu rekordu: " + e);
            }
        return "index.xhtml";
    }


    public String deleteZawodnicy(){

        for(String zToDel : this.chosenZawodnicy){

            try {
                entityManager.getTransaction().begin();

                log.info("usuwanie ");
                //entityManager.createQuery("DELETE FROM ZawodnikEntity z WHERE z.id = :idd").setParameter("idd",Integer.parseInt(zToDel));
                int result = entityManager.createQuery("DELETE FROM ZawodnikEntity WHERE id =" + zToDel).executeUpdate();
                entityManager.getTransaction().commit();
                log.info("Rows affected: " + result);
                this.zawodnicy = entityManager.createQuery("SELECT z FROM ZawodnikEntity z", ZawodnikEntity.class).getResultList();


               // entityManager.close();

            }
            catch(Exception e) {
                log.info("Blad przy usuwaniu rekordu: " + e);
            }
        }

        return "index.xhtml";
    }



    public String updateZawodnik(){

        try {

            entityManager.getTransaction().begin();

            log.info("aktualizowanie ");

            String hql = "UPDATE ZawodnikEntity set id = :idd, imie = :imiee, nazwisko = :nazwiskoo, punkty = :punktyy, narodowosc = :narodowoscc  "  +
                    "WHERE id ="+idToUpdate;
            Query query = entityManager.createQuery(hql);
            query.setParameter("idd", Integer.parseInt(id));
            query.setParameter("imiee", imie);
            query.setParameter("nazwiskoo", nazwisko);
            query.setParameter("punktyy", Integer.parseInt(punkty));
            query.setParameter("narodowoscc", narodowosc);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            //int result = entityManager.createQuery("INSERT INTO ZawodnikEntity VALUES("+id+","+imie+","+nazwisko+","+punkty+","+narodowosc+")").executeUpdate();
            entityManager.getTransaction().commit();
            entityManager.clear();
            this.zawodnicy.clear();
            this.zawodnicy = entityManager.createQuery("SELECT z FROM ZawodnikEntity z", ZawodnikEntity.class).getResultList();


            // entityManager.close();

        }
        catch(Exception e) {
            log.info("Blad przy aktualizowaniu rekordu: " + e);
        }

        return "index.xhtml";
    }

    public List<String> getChosenZawodnicy() {
        return chosenZawodnicy;
    }

    public void setChosenZawodnicy(List<String> chosenZawodnicy) {
        this.chosenZawodnicy = chosenZawodnicy;
    }

    public ArrayList<String> getRecordsDb() {
        return recordsDb;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public String getChosenAction() {
        return chosenAction;
    }

    public void setChosenAction(String chosenAction) {
        this.afterChosenAction = true;
        this.chosenAction = chosenAction;
    }

    public ArrayList<String> getDbs() {
        return dbs;
    }

    public void setDbs(ArrayList<String> dbs) {
        this.dbs = dbs;
    }

    public String getChosenDb() {
        return chosenDb;
    }

    public String getIdToUpdate() {
        return idToUpdate;
    }

    public void setIdToUpdate(String idToUpdate) {
        this.idToUpdate = idToUpdate;
        entityManager.getTransaction().begin();
        ZawodnikEntity z = entityManager.createQuery("SELECT z FROM ZawodnikEntity z WHERE z.id ="+idToUpdate, ZawodnikEntity.class).getSingleResult();
        entityManager.getTransaction().commit();
        this.id = String.valueOf(z.getId());
        this.imie = z.getImie();
        this.nazwisko = z.getNazwisko();
        this.punkty = String.valueOf(z.getPunkty());
        this.narodowosc = z.getNarodowosc();
    }

    public boolean isAfterChosenAction() {
        return afterChosenAction;
    }

    public void setChosenDb(String chosenDb) {
        this.actionsRendered = true;
        this.chosenDb = chosenDb;
        recordsDb.clear();

        try {
            entityManager.getTransaction().begin();

            this.zawodnicy = entityManager.createQuery("SELECT z FROM ZawodnikEntity z", ZawodnikEntity.class).getResultList();

            this.sklepy = entityManager.createQuery("SELECT s FROM SklepEntity s", SklepEntity.class).getResultList();
            entityManager.getTransaction().commit();
            //entityManager.close();

        }
        catch(Exception e) {
            log.info("Blad przy ladowaniu: " + e);
        }
        if(this.chosenDb.equals("zawodnik")){
            for (ZawodnikEntity s : zawodnicy){
                recordsDb.add(s.toString());
                //log.info(s.toString());
            }


        }else{
            if(this.chosenDb.equals("sklep")){
                for (SklepEntity s : sklepy){
                    recordsDb.add(s.toString());
                    //log.info(s.toString());
                }
            }
        }
    }

    public List<ZawodnikEntity> getZawodnicy() {
        return zawodnicy;
    }

    public boolean isActionsRendered() {
        return actionsRendered;
    }

    public void setZawodnicy(List<ZawodnikEntity> zawodnicy) {
        this.zawodnicy = zawodnicy;
    }

    public List<SklepEntity> getSklepy() {
        return sklepy;
    }

    public void setSklepy(List<SklepEntity> sklepy) {
        this.sklepy = sklepy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPunkty() {
        return punkty;
    }

    public void setPunkty(String punkty) {
        this.punkty = punkty;
    }

    public String getNarodowosc() {
        return narodowosc;
    }

    public void setNarodowosc(String narodowosc) {
        this.narodowosc = narodowosc;
    }


}
