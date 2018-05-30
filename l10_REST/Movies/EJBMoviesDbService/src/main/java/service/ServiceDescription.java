package service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "service_description")
public class ServiceDescription implements Serializable{

    private URI movies;
    private URI movieById;
    private URI movieByTitle;

    public ServiceDescription() {
        try {
            String baseUri = "localhost:8080/RestMovies/rest";
            this.movies = new URI(baseUri + "/movies");
            this.movieByTitle = new URI(baseUri + "/movies/title");
            this.movieById = new URI(baseUri + "/movies/1");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public URI getMovies() {
        return movies;
    }

    public void setMovies(URI movies) {
        this.movies = movies;
    }

    public URI getMovieById() {
        return movieById;
    }

    public void setMovieById(URI movieById) {
        this.movieById = movieById;
    }

    public URI getMovieByTitle() {
        return movieByTitle;
    }

    public void setMovieByTitle(URI movieByTitle) {
        this.movieByTitle = movieByTitle;
    }
}
