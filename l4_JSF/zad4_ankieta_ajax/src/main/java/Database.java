import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;

@ManagedBean(eager = true)
@ApplicationScoped
public class Database {

    HashMap<String, Client> database;

    public Database() {
        this.database = new HashMap<String, Client>();
    }

}
