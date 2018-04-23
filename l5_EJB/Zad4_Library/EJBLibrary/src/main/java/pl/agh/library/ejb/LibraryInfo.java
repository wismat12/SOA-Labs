package pl.agh.library.ejb;

public interface LibraryInfo {

    int getMaxBookId();

    String getId(int id);

    public String getAuthor(int id) ;

    public String getTitle(int id);

    public String getGenre(int id);

    public String getPublish_date(int id);

    public String getDescription(int id);

    public boolean isAvailable(int id);

    public boolean isBooked(int id);

}
