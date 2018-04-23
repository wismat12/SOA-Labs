package pl.agh.library.ejb;

public interface LibraryLibrarian {

    void borrowAbook(int id);
    void bookAbook(int id);
    void returnAbook(int id);
    void unbookAbook(int id);
}
