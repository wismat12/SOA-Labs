package pl.agh.soa.rest.movies;



import dao.MoviesDAO;
import jpaPackage.MovieEntity;
import service.ServiceDescription;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//http://www.vogella.com/tutorials/REST/article.html

//http://localhost:8080/RestMovies/rest/movies
//GET - listuje zawartość kolekcji jak i pojedynczego elementu

@Path("")
public class MoviesRestService {

    private final Logger logger = Logger.getLogger(String.valueOf(MoviesRestService.class));

    private MoviesDAO moviesDAOService;

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    public MoviesRestService() {

        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(javax.naming.Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put("jboss.naming.client.ejb.context", true);

        try {
            final javax.naming.Context context = new InitialContext(jndiProperties);

            moviesDAOService = lookupmoviesDAOServiceEJB(context);

            logger.info("Connection with ejb database made!");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static MoviesDAO lookupmoviesDAOServiceEJB(javax.naming.Context context)  throws NamingException {

        return (MoviesDAO) context.lookup("ejb:/EJBMoviesDbService-1.0-SNAPSHOT/MoviesDAOImpl!dao.MoviesDAO");
    }

    @Path("/movies")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response movies() {
        return Response.status(Response.Status.OK).entity(moviesDAOService.getMovies()).build();
    }
    //@Path("/movies/{id:[1-9][0-9]+}")
    @Path("/movies/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response moviesById(@PathParam("id") int id) {
        MovieEntity foundMovie = moviesDAOService.getMovieById(id);
        if (foundMovie == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(foundMovie).build();
    }

    @Path("/movies/title/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response moviesByTitle(@PathParam("title") String title) {
        MovieEntity foundMovie = moviesDAOService.getMovieByTitle(title.replace('_', ' '));
        if (foundMovie == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.OK).entity(foundMovie).build();
    }

    @Path("/movies/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
       logger.info("Usuwanie filmu: " + moviesDAOService.getMovieById(id));
       int ret = moviesDAOService.remove(id);
        if (ret > 0)
            return Response.status(Response.Status.OK).build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Path("/movies/conneg")
    @GET
    @Produces("text/uri-list")
    public Response getContentUris() {
        List<URI> moviesUris = moviesDAOService.getMovies().stream().map(movie -> movie.getUri()).collect(Collectors.toList());
        return Response.status(Response.Status.OK).entity(moviesUris.toString()).build();
    }

    @Path("/art")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response redirect() {
        try {
            return Response.status(Response.Status.MOVED_PERMANENTLY).location(new URI("http://localhost:8080/RestMovies/rest/movies")).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ERROR WHILE REDIRECTING").build();
    }

    @Path("/service-desc")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response moviesDesc() {
        ServiceDescription serviceDesc = moviesDAOService.getServiceDesc();
        return Response.status(Response.Status.OK).entity(serviceDesc).build();
    }

    @Path("/movies/{id}")
    @POST
    @Produces(MediaType.TEXT_HTML)
    public Response forbiddenPOST(@PathParam("id") int id) {
        //ServiceDescription serviceDesc = moviesDAOService.getServiceDesc();
        return Response.status(Response.Status.FORBIDDEN).entity(Response.Status.FORBIDDEN).build();
    }
}
