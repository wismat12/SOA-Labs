import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;

@ManagedBean
@SessionScoped
public class Client {

    private String name;
    private String email;
    private int age;
    private String sex;
    private String education;
    private int height;

    private HashMap<String,String> additionalInfo;
    private boolean sexChosen;

    private String newClientQuestion1;
    private String newClientQuestion2;
    private String newClientQuestion3;
    private String[] newClientQuestions4;

    private String oldClientQuestion1;
    private String oldClientQuestion2;
    private String oldClientQuestion3;
    private String oldClientQuestion4;

    public Client(){
        this.additionalInfo = new HashMap<String, String>();
        this.sexChosen = false;
        this.email = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        this.sexChosen = true;

            if(sex.equals("male")){
                this.additionalInfo.put("klatka","");
                this.additionalInfo.put("pas","");
                this.additionalInfo.put("dlugosc_nogi","");
            }else{
                this.additionalInfo.put("biust","");
                this.additionalInfo.put("miseczka","");
                this.additionalInfo.put("talia","");
                this.additionalInfo.put("biodra","");
                this.additionalInfo.put("dlugosc_nogi","");
            }

    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public HashMap<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    public boolean isSexChosen() {
        return sexChosen;
    }

    public void setSexChosen(boolean sexChosen) {
        this.sexChosen = sexChosen;
    }

    public String getNewClientQuestion1() {
        return newClientQuestion1;
    }

    public void setNewClientQuestion1(String newClientQuestion1) {
        this.newClientQuestion1 = newClientQuestion1;
    }

    public String getNewClientQuestion2() {
        return newClientQuestion2;
    }

    public void setNewClientQuestion2(String newClientQuestion2) {
        this.newClientQuestion2 = newClientQuestion2;
    }

    public String getNewClientQuestion3() {
        return newClientQuestion3;
    }

    public void setNewClientQuestion3(String newClientQuestion3) {
        this.newClientQuestion3 = newClientQuestion3;
    }

    public String[] getNewClientQuestions4() {
        return newClientQuestions4;
    }

    public void setNewClientQuestions4(String[] newClientQuestions4) {
        this.newClientQuestions4 = newClientQuestions4;
    }

    public String getOldClientQuestion1() {
        return oldClientQuestion1;
    }

    public void setOldClientQuestion1(String oldClientQuestion1) {
        this.oldClientQuestion1 = oldClientQuestion1;
    }

    public String getOldClientQuestion2() {
        return oldClientQuestion2;
    }

    public void setOldClientQuestion2(String oldClientQuestion2) {
        this.oldClientQuestion2 = oldClientQuestion2;
    }

    public String getOldClientQuestion3() {
        return oldClientQuestion3;
    }

    public void setOldClientQuestion3(String oldClientQuestion3) {
        this.oldClientQuestion3 = oldClientQuestion3;
    }

    public String getOldClientQuestion4() {
        return oldClientQuestion4;
    }

    public void setOldClientQuestion4(String oldClientQuestion4) {
        this.oldClientQuestion4 = oldClientQuestion4;
    }
}
