package pl.agh.soa.rest.service;

import pl.agh.soa.rest.dao.CatsDao;
import pl.agh.soa.rest.model.Cat;
import pl.agh.soa.rest.model.Cats;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.util.logging.Logger;

//http://localhost:8080/RestCats/meow/{id}

public class CatRestService {
    private final Logger logger = Logger.getLogger(String.valueOf(CatRestService.class));

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;

    public CatRestService(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getCatHTML() {
        for(Cat c : CatsDao.instance.getContentProvider()){
            if(c.getId().equals(id)){
                return Response.status(200).entity(c).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Error 404 - nie znaleziono kota o id " + id).build();
    }
    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getCat() {

        for(Cat c : CatsDao.instance.getContentProvider()){
            if(c.getId().equals(id)){
                return Response.status(200).entity(c).build();
            }
        }
        logger.info("test app xml/json");
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //tylko pojedynczy element
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putCat(Cat cat) {

        for(int i = 0; i < CatsDao.instance.getContentProvider().size(); i++){
            if(CatsDao.instance.getContentProvider().get(i).getId().equals(cat.getId())){
                CatsDao.instance.getContentProvider().remove(i);
                CatsDao.instance.getContentProvider().add(cat);
                CatsDao.instance.saveContentProvider();
                return Response.status(201).entity(cat).build(); //created
            }
        }
        CatsDao.instance.getContentProvider().add(cat);
        CatsDao.instance.saveContentProvider();
        return Response.status(201).entity(cat).build(); //created
    }

    //tylko pojedynczy element
    @DELETE
    public Response deleteCat() {

        for(int i = 0; i < CatsDao.instance.getContentProvider().size(); i++){
            if(CatsDao.instance.getContentProvider().get(i).getId().equals(id)){
                CatsDao.instance.getContentProvider().remove(i);
                CatsDao.instance.saveContentProvider();
                return Response.status(200).build(); //created
            }
        }
        return Response.status(404).build(); //notFound
    }



}
