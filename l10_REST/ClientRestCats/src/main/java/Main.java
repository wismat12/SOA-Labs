import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ClientResponse;
import pl.agh.soa.rest.model.Cat;
import pl.agh.soa.rest.model.Cats;


import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Scanner;

import javax.ws.rs.client.Client;



public class Main {

    private static final String path = "http://localhost:8080/RestCats/meow";

    public static void main(String[] args) {

        getCats(MediaType.APPLICATION_JSON_TYPE);
        System.out.println();
        getCats(MediaType.APPLICATION_XML_TYPE);
        System.out.println();

        getCat(MediaType.APPLICATION_JSON_TYPE, "1");
        System.out.println();
        getCat(MediaType.APPLICATION_JSON_TYPE, "100");
        System.out.println();

        Cat perelka = new Cat();
        perelka.setId("5");
        perelka.setName("Perelka");
        perelka.setGender("Kotka");
        perelka.setCoat("Takie rysiowe");
        perelka.setBreed("Maine Coon");

        addCat(MediaType.APPLICATION_JSON_TYPE,perelka);
        System.out.println();
        getCats(MediaType.APPLICATION_XML_TYPE);
        System.out.println();

        Cat puszek = new Cat();
        puszek.setId("7");
        puszek.setName("Puszek");
        puszek.setGender("Kot");
        puszek.setCoat("Bialy z czarnym pyszczkiem");
        puszek.setBreed("Birmanski");

        deleteCat(MediaType.APPLICATION_JSON_TYPE,perelka.getId());
        System.out.println();
        getCats(MediaType.APPLICATION_JSON_TYPE);
        System.out.println();

        Cats catsy = new Cats();
        catsy.getCats().add(perelka);
        catsy.getCats().add(puszek);
        postCats(MediaType.APPLICATION_JSON_TYPE,catsy);
        System.out.println();
        getCats(MediaType.APPLICATION_JSON_TYPE);
        System.out.println();

    }


    public static void getCats(MediaType mediaType){

        URI baseUri = UriBuilder.fromUri(path).build();
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target(baseUri);

        ClientResponse response = null;

        response = (ClientResponse) target.path("cats").request().accept(mediaType).get();

        System.out.println("response status: " + response.getStatusInfo());
        System.out.println("media type: " + mediaType);

        Cats catsObj = response.readEntity(Cats.class);

        System.out.println("Cats from object Cats");
        System.out.println(catsObj);

        response.close();

    }
    public static void getCat(MediaType mediaType, String id){

        URI baseUri = UriBuilder.fromUri(path).build();
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target(baseUri);

        ClientResponse response = null;

        response = (ClientResponse) target.path("cats").path(id).request().accept(mediaType).get();

        System.out.println("response status: " + response.getStatusInfo());
        System.out.println("media type: " + mediaType);

        if(response.getStatus() == 404){
            System.out.println("Error 404: - brak kota o id " + id);
            response.close();
            return;
        }

        Cat catObj = response.readEntity(Cat.class);

        System.out.println("Cat from object Cat");
        System.out.println(catObj);

        response.close();

    }

    public static void addCat(MediaType mediaType, Cat cat){

        URI baseUri = UriBuilder.fromUri(path).build();
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target(baseUri);

        ClientResponse response = (ClientResponse) target.path("cats").path(cat.getId()).request(mediaType).put(Entity.entity(cat,mediaType));

        System.out.println("response status: " + response.getStatusInfo());
        System.out.println("media type: " + mediaType);

        Cat catObj = response.readEntity(Cat.class);

        System.out.println("added - Cat from object Cat");
        System.out.println(catObj);

        response.close();

    }

    public static void deleteCat(MediaType mediaType, String id){

        URI baseUri = UriBuilder.fromUri(path).build();
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target(baseUri);

        ClientResponse response = (ClientResponse) target.path("cats").path(id).request().delete();

        System.out.println("response status: " + response.getStatusInfo());
        System.out.println("media type: " + mediaType);
        if(response.getStatus() == 404){
            System.out.println("Error 404: - brak kota o id " + id);
            response.close();
            return;
        }
        response.close();

    }
    public static void postCats(MediaType mediaType, Cats cats){

        URI baseUri = UriBuilder.fromUri(path).build();
        Client client = ResteasyClientBuilder.newClient();
        WebTarget target = client.target(baseUri);

        ClientResponse response = (ClientResponse) target.path("cats").request(mediaType).post(Entity.entity(cats,mediaType));

        System.out.println("response status: " + response.getStatusInfo());
        System.out.println("media type: " + mediaType);
        System.out.println("Posted new Cats Collection ");
        response.close();
    }



}
