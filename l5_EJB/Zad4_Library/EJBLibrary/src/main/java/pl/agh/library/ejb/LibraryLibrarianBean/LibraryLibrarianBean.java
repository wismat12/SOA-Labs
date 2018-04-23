package pl.agh.library.ejb.LibraryLibrarianBean;

import pl.agh.library.ejb.LibraryLibrarian;
import pl.agh.library.ejb.LibraryCatalog;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
@Remote(LibraryLibrarian.class)
public class LibraryLibrarianBean implements LibraryLibrarian {

    private static final Logger logger =  Logger.getLogger(String.valueOf(LibraryLibrarianBean.class));

    @EJB
    LibraryCatalog catalog;

    @Override
    public void borrowAbook(int id) {
        this.catalog.borrowAbook(id);
        logger.info("wypozyczono "+this.catalog.getBooksCatalog().get(id).getTitle());
    }

    @Override
    public void bookAbook(int id) {
        this.catalog.bookAbook(id);
        logger.info("zarezerwowano "+this.catalog.getBooksCatalog().get(id).getTitle());
    }

    @Override
    public void returnAbook(int id) {
        this.catalog.returnAbook(id);
        logger.info("oddano "+this.catalog.getBooksCatalog().get(id).getTitle());
    }

    @Override
    public void unbookAbook(int id) {
        this.catalog.unbookAbook(id);
        logger.info("zniesiono rezerwacje "+this.catalog.getBooksCatalog().get(id).getTitle());
    }
}
