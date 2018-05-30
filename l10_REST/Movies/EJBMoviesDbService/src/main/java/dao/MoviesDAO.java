package dao;

import jpaPackage.MovieEntity;
import service.ServiceDescription;

import java.util.List;

public interface MoviesDAO {

    List<MovieEntity> getMovies();

    MovieEntity getMovieByTitle(String title);

    MovieEntity getMovieById(int id);

    int remove(int id);

    ServiceDescription getServiceDesc();
}
