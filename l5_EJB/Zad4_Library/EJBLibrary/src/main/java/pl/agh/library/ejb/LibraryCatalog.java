package pl.agh.library.ejb;

//import com.thoughtworks.xstream.*;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

@Singleton
@Startup
public class LibraryCatalog {

    private static final Logger logger = Logger.getLogger(String.valueOf(LibraryCatalog.class));
    private List<Book> books;

    @PostConstruct
    public void setupCatalog() {
        //Unmarshalling: Converting XML content to Java objects
        this.books = new ArrayList<Book>();
        try {
            books = JAXBXMLHandler.unmarshal(new File("data/libraryBase.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        logger.info((books.toString()));
        logger.info("Inicjalizacyjne wczytanie katalogu ksiazek z pliku xml");
    }

    @Lock(READ)
    public List<Book> getBooksCatalog() {
        return this.books;
    }

    @Lock(WRITE)
    public void borrowAbook(int id) {
        Book book = this.getBooksCatalog().get(id);
        book.setAvailable(false);
        //Marshalling: Writing Java objects to XMl file /Unmarshalling: Converting XML content to Java objects
        this.saveAndReadCatalog("borrowAbook Action saved");
    }

    @Lock(WRITE)
    public void unbookAbook(int id) {
        Book book = this.getBooksCatalog().get(id);
        book.setBooked(false);
        this.saveAndReadCatalog("unbookAbook Action saved");

    }
    @Lock(WRITE)
    public void bookAbook(int id) {
        Book book = this.getBooksCatalog().get(id);
        book.setBooked(true);
        this.saveAndReadCatalog("bookAbook Action saved");

    }
    @Lock(WRITE)
    public void returnAbook(int id) {
        Book book = this.getBooksCatalog().get(id);
        book.setAvailable(true);
        book.setBooked(false);
        this.saveAndReadCatalog("returnAbook Action saved");
    }

    private void saveAndReadCatalog(String action){
        //Marshalling: Writing Java objects to XMl file /Unmarshalling: Converting XML content to Java objects
        try {
            JAXBXMLHandler.marshal(this.books, new File("data/libraryBase.xml"));
            this.books = JAXBXMLHandler.unmarshal(new File("data/libraryBase.xml"));
            logger.info(action);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
/*
    public static ArrayList<Book> XStreamRead(){

        XStream xstream = new XStream();

        xstream.alias("catalog", ArrayList.class);
        xstream.alias("book", Book.class);

        File file = new File("libraryBase.xml");

        @SuppressWarnings("unchecked")
        ArrayList<Book> catalog = (ArrayList<Book>) xstream.fromXML(String.valueOf(file));

        return catalog;
    }

    public static void XStreamSave(ArrayList<Book> catalog){

        XStream xstream = new XStream();
        OutputStream file;
        Writer writer;
        try {
            file = new FileOutputStream("libraryBase.xml");

            writer = new OutputStreamWriter(file, Charset.forName("UTF-8"));

            xstream.alias("catalog", ArrayList.class);
            xstream.alias("book", Book.class);

            xstream.toXML(catalog, writer);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }
    */
}
