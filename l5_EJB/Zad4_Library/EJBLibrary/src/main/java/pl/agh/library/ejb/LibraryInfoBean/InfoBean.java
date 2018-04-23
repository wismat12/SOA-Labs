package pl.agh.library.ejb.LibraryInfoBean;

import pl.agh.library.ejb.LibraryCatalog;
import pl.agh.library.ejb.LibraryInfo;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(LibraryInfo.class)
public class InfoBean implements LibraryInfo {

    @EJB
    LibraryCatalog catalog;

    @Override
    public int getMaxBookId() {
        return Integer.valueOf(this.catalog.getBooksCatalog().get(this.catalog.getBooksCatalog().size() - 1).getId());
    }

    @Override
    public String getId(int id) {
        return this.catalog.getBooksCatalog().get(id).getId();
    }

    @Override
    public String getAuthor(int id) {
        return this.catalog.getBooksCatalog().get(id).getAuthor();
}

    @Override
    public String getTitle(int id) {
        return this.catalog.getBooksCatalog().get(id).getTitle();
    }

    @Override
    public String getGenre(int id) {
        return this.catalog.getBooksCatalog().get(id).getGenre();
    }

    @Override
    public String getPublish_date(int id) {
        return this.catalog.getBooksCatalog().get(id).getPublish_date();
    }

    @Override
    public String getDescription(int id) {
        return this.catalog.getBooksCatalog().get(id).getDescription();
    }

    @Override
    public boolean isAvailable(int id) {
        return this.catalog.getBooksCatalog().get(id).isAvailable();
    }

    @Override
    public boolean isBooked(int id) {
        return this.catalog.getBooksCatalog().get(id).isBooked();
    }
}
