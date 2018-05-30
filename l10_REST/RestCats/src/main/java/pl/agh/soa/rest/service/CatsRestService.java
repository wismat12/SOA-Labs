package pl.agh.soa.rest.service;


import pl.agh.soa.rest.dao.CatsDao;
import pl.agh.soa.rest.model.Cat;
import pl.agh.soa.rest.model.Cats;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//http://www.vogella.com/tutorials/REST/article.html
//http://localhost:8080/RestCats/meow/cats
//GET - listuje zawartość kolekcji jak i pojedynczego elementu

@Path("/cats")
public class CatsRestService {

    private final Logger logger = Logger.getLogger(String.valueOf(CatsRestService.class));

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    // Return the list of cats to the user in the browser
    @GET
    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
    public Response getCatsBrowser(){
        Cats cats1 = new Cats();
        cats1.getCats().addAll(CatsDao.instance.getContentProvider());

        return Response.status(200).entity(cats1).build();
    }

    @POST
    @Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
    public Response getCatsBrowser(Cats cats){
        CatsDao.instance.getContentProvider().clear();
        CatsDao.instance.getContentProvider().addAll(cats.getCats());
        CatsDao.instance.saveContentProvider();

        return Response.status(201).build();
    }

    @Path("{id}")
    public CatRestService getTodo(@PathParam("id") String id) {
        //logger.info("catRESTsERVICE");
        return new CatRestService(uriInfo, request, id);
    }
}
