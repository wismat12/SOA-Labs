package dao;

import jpaPackage.MovieEntity;
import service.ServiceDescription;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

@Singleton
@Startup
@Remote(MoviesDAO.class)
public class MoviesDAOImpl implements MoviesDAO, Serializable{

    private static final Logger logger =  Logger.getLogger(String.valueOf(MoviesDAOImpl.class));

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void loadMovies() {
        //entityManager.getTransaction().begin();
        entityManager.persist(new MovieEntity("Avengers"));
        entityManager.persist(new MovieEntity("Avengers 2"));
        entityManager.persist(new MovieEntity("Avengers 3"));
        entityManager.persist(new MovieEntity("Deadpool"));
        //entityManager.getTransaction().commit();
        //this.movies = entityManager.createQuery("SELECT a FROM MovieEntity a", MovieEntity.class).getResultList();
    }

    @Lock(READ)
    @Override
    public List<MovieEntity> getMovies() {
        List<MovieEntity> movies = entityManager.createQuery("SELECT a FROM MovieEntity a", MovieEntity.class).getResultList();
        return movies;
    }

    @SuppressWarnings("Duplicates")
    @Lock(READ)
    @Override
    public MovieEntity getMovieByTitle(String title) {
        String hql = "SELECT a  FROM MovieEntity a  WHERE lower(title) LIKE :titleParam";
        Query query = entityManager.createQuery(hql, MovieEntity.class);
        query.setParameter("titleParam", title);
        try {
            MovieEntity movie =(MovieEntity) query.getSingleResult();
            return movie;
        } catch (NoResultException e) {
            logger.info("No result found for movie called: " + title);
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    @Lock(READ)
    @Override
    public MovieEntity getMovieById(int id) {
        String hql = "SELECT a  FROM MovieEntity a  WHERE id =:idParam";
        Query query = entityManager.createQuery(hql, MovieEntity.class);
        query.setParameter("idParam", id);
        try {
            MovieEntity movie =(MovieEntity) query.getSingleResult();
            return movie;
        } catch (NoResultException e) {
            logger.info("No result found for movie called: " + id);
            return null;
        }
    }

    @Lock(WRITE)
    @Override
    public int remove(int id) {
        try {
            int result = entityManager.createQuery("DELETE FROM MovieEntity WHERE id =" + String.valueOf(id)).executeUpdate();
            logger.info("Rows affected: " + result);
            return result;
        }
        catch(Exception e) {
            logger.info("Blad przy usuwaniu rekordu: " + e);
        }
        return 0;
    }

    @Lock(READ)
    @Override
    public ServiceDescription getServiceDesc() {
        return new ServiceDescription();
    }
}
