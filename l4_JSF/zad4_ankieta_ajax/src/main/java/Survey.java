import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(eager = true)
@SessionScoped
public class Survey implements Serializable{

    @ManagedProperty(value = "#{database}")
    private Database db;

    private String usersMail;

    private Client currentClient;

    private boolean initMailSet;

    private boolean initWelcomeRender;
    private boolean welcomeClientRender;
    private boolean welcomeNewClientRender;
    private boolean questionsOrSatisfactionRender;

    private boolean newClient;

    public Survey() {

        this.initWelcomeRender = true;
        this.welcomeClientRender = false;
        this.welcomeNewClientRender = false;
        this.questionsOrSatisfactionRender = false;
        this.initMailSet = true;

    }

    public void setDb(Database db) {
        this.db = db;
    }

    public String getUsersMail() {
        return usersMail;
    }

    public void setUsersMail(String usersMail) {

        if(initMailSet){
            this.usersMail = usersMail;
            if(this.db.database.containsKey(this.usersMail)){
                this.newClient = false;
                this.currentClient = this.db.database.get(this.usersMail);
                this.initWelcomeRender = false;
                this.welcomeClientRender = true;
            }else{
                this.newClient = true;
                this.currentClient = new Client();
                this.currentClient.setEmail(usersMail);
                this.db.database.put(usersMail,  this.currentClient);
                this.welcomeNewClientRender = true;
                this.initWelcomeRender = false;
            }
        }
        this.initMailSet = false;
    }

    public boolean isInitWelcomeRender() {
        return initWelcomeRender;
    }

    public void setInitWelcomeRender(boolean initWelcomeRender) {
        this.initWelcomeRender = initWelcomeRender;
    }

    public boolean isWelcomeClientRender() {
        return welcomeClientRender;
    }

    public void setWelcomeClientRender(boolean welcomeClientRender) {
        this.welcomeClientRender = welcomeClientRender;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public boolean isWelcomeNewClientRender() {
        return welcomeNewClientRender;
    }

    public void setWelcomeNewClientRender(boolean welcomeNewClientRender) {
        this.welcomeNewClientRender = welcomeNewClientRender;
    }

    public String getAdditionalInfoPapge(){
        if(this.usersMail!=null){
            if(this.currentClient.getSex()!=null){
                if(this.currentClient.getSex().equals("male")){
                    return "maleInfo.xhtml";
                }else {
                    return "femaleInfo.xhtml";
                }
            }
        }
        return null;
    }

    public String getIfClientIsNew(){
        if(this.questionsOrSatisfactionRender){
            if(this.newClient){
                return "questions.xhtml";
            }else {
                return "satisfactionLvl.xhtml";
            }
        }else{
            return null;
        }
    }

    public void enableQuestionsOrSatifactionRender(){
        this.questionsOrSatisfactionRender = true;
    }

    public String getCommonInfoPage(){
        if(this.welcomeNewClientRender || this.welcomeClientRender){
            return "commonInfo.xhtml";
        }else{
            return null;
        }
    }

    public String[] getAppropriateList(){
        if(this.currentClient.getSex().equals("male")){
            String[] list = {"spodnie","spodenki","krawaty","garnitury","koszule"};
            return list;
        }else {
            String[] list = {"garsonki","bluzki","spódniczki","spodnie"};
            return list;
        }
    }


    public boolean isQuestionsOrSatisfactionRender() {
        return questionsOrSatisfactionRender;
    }

    public void setQuestionsOrSatisfactionRender(boolean questionsOrSatisfactionRender) {
        this.questionsOrSatisfactionRender = questionsOrSatisfactionRender;
    }

    public boolean isNewClient() {
        return newClient;
    }

    public void setNewClient(boolean newClient) {
        this.newClient = newClient;
    }

    public String gotoSummary(){
        //preparing for new survey entry
        this.usersMail = null;
        this.initWelcomeRender = true;
        this.initMailSet = true;
        this.welcomeNewClientRender = false;
        this.welcomeClientRender = false;
        this.questionsOrSatisfactionRender = false;
        return "summary.xhtml";
    }
}
