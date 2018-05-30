package jpaPackage;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

@Entity
@XmlRootElement(name = "movie")
@Table(name = "movies")
public class MovieEntity implements Serializable {

    @Transient
    @XmlTransient
    private final String baseUri = "localhost:8080/RestMovies/rest/movies/title";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlAttribute
    private int id;
    @XmlAttribute
    private String title;
    @XmlAttribute
    private URI uri;

    public MovieEntity(String title) {
        this.title = title;
        try {
            this.uri = new URI(baseUri + "/" + title.replace(' ', '_'));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public MovieEntity() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URI getUri() {
        return uri;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", uri=" + uri +
                '}';
    }
}
