import pl.agh.library.ejb.LibraryInfo;
import pl.agh.library.ejb.LibraryLibrarian;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

@ManagedBean(eager = true)
@SessionScoped
public class LibrarianClient implements Serializable{

    private LibraryInfo infoService;
    private LibraryLibrarian librarianService;

    private ArrayList<Integer>  availableBooks;
    private ArrayList<Integer>  bookedBooks;
    private ArrayList<Integer>  borrowedBooks;

    public LibrarianClient() {

        this.availableBooks = new ArrayList<>();
        this.bookedBooks = new ArrayList<>();
        this.borrowedBooks = new ArrayList<>();

        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put("jboss.naming.client.ejb.context", true);

        try {
            final Context context = new InitialContext(jndiProperties);

            infoService = lookupLibraryInfoEJB(context);
            librarianService = lookupLibraryLibrarianEJB(context);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        this.goThroughCatalog();

    }

    private static LibraryInfo lookupLibraryInfoEJB(Context context)  throws NamingException {

        return (LibraryInfo) context.lookup("ejb:/EJBLibrary/InfoBean!pl.agh.library.ejb.LibraryInfo");
    }

    private static LibraryLibrarian lookupLibraryLibrarianEJB(Context context)  throws NamingException {

        return (LibraryLibrarian) context.lookup("ejb:/EJBLibrary/LibraryLibrarianBean!pl.agh.library.ejb.LibraryLibrarian");
    }

    private void goThroughCatalog(){
        for(int i = 1; i <= this.infoService.getMaxBookId(); i++){
            if(this.infoService.isBooked(i)){
                this.bookedBooks.add(i);
            }else{
                if(this.infoService.isAvailable(i)){
                    this.availableBooks.add(i);
                }else{
                    this.borrowedBooks.add(i);
                }
            }
        }
    }

    public ArrayList<Integer> getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(ArrayList<Integer> availableBooks) {
        this.availableBooks = availableBooks;
    }

    public ArrayList<Integer> getBookedBooks() {
        return bookedBooks;
    }

    public void setBookedBooks(ArrayList<Integer> bookedBooks) {
        this.bookedBooks = bookedBooks;
    }

    public ArrayList<Integer> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Integer> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public LibraryInfo getInfoService() {
        return infoService;
    }
}
