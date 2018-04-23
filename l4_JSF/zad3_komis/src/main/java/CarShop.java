import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@ManagedBean
@SessionScoped
public class CarShop implements Serializable{

    private ArrayList<String> carMakeList;  //marki

                    //marka        model  / konkretny samochod
    private HashMap<String, HashMap<String, ArrayList<Car> > > modelsDB;

    private String chosenCarMake;
    private String chosenCarModel;

    private double from;
    private double to;
    private String engine;
    private String clientName;
    private String clientPhone;

    private Set<String> modelsList;
    private ArrayList<String> carsList;

    private boolean renderModelList;
    private boolean renderOutput;
    private boolean renderForm;
    private boolean notCorrectlyFilled;

    private boolean validNumber;
    private boolean validPrice;
    private boolean validName;

    public CarShop(){
        this.carMakeList = new ArrayList<String>();
        this.carMakeList.add("BMW");
        this.carMakeList.add("Audi");
        this.carMakeList.add("Porsche");

        this.modelsDB = new HashMap<String, HashMap<String,ArrayList<Car>>>();
        this.modelsDB.put("BMW",new HashMap<String,ArrayList<Car>>());
        this.modelsDB.put("Audi",new HashMap<String,ArrayList<Car>>());
        this.modelsDB.put("Porsche",new HashMap<String,ArrayList<Car>>());

        this.modelsDB.get("BMW").put("B1", new ArrayList<Car>());
        this.modelsDB.get("BMW").get("B1").add(new Car("B11",200000,"ON"));
        this.modelsDB.get("BMW").get("B1").add(new Car("B12",240000,"ON"));
        this.modelsDB.get("BMW").get("B1").add(new Car("B13",130000,"benzyna"));

        this.modelsDB.get("BMW").put("B2", new ArrayList<Car>());
        this.modelsDB.get("BMW").get("B2").add(new Car("B21",123000,"ON"));
        this.modelsDB.get("BMW").get("B2").add(new Car("B22",240420,"ON"));
        this.modelsDB.get("BMW").get("B2").add(new Car("B23",123000,"benzyna"));

        this.modelsDB.get("BMW").put("B3", new ArrayList<Car>());
        this.modelsDB.get("BMW").get("B3").add(new Car("B31",200000,"ON"));
        this.modelsDB.get("BMW").get("B3").add(new Car("B32",240000,"ON"));
        this.modelsDB.get("BMW").get("B3").add(new Car("B33",130000,"benzyna"));

        this.modelsDB.get("Audi").put("A1", new ArrayList<Car>());
        this.modelsDB.get("Audi").get("A1").add(new Car("B11",312132,"ON"));
        this.modelsDB.get("Audi").get("A1").add(new Car("B12",112233,"ON"));
        this.modelsDB.get("Audi").get("A1").add(new Car("B13",132132,"benzyna"));

        this.modelsDB.get("Audi").put("A2", new ArrayList<Car>());
        this.modelsDB.get("Audi").get("A2").add(new Car("A11",231312,"ON"));
        this.modelsDB.get("Audi").get("A2").add(new Car("A12",113112,"ON"));
        this.modelsDB.get("Audi").get("A2").add(new Car("A13",231312,"benzyna"));

        this.modelsDB.get("Audi").put("A3", new ArrayList<Car>());
        this.modelsDB.get("Audi").get("A3").add(new Car("A11",321244,"ON"));
        this.modelsDB.get("Audi").get("A3").add(new Car("A12",132111,"ON"));
        this.modelsDB.get("Audi").get("A3").add(new Car("A13",123124,"benzyna"));

        this.modelsDB.get("Porsche").put("P1", new ArrayList<Car>());
        this.modelsDB.get("Porsche").get("P1").add(new Car("P11",231213,"ON"));
        this.modelsDB.get("Porsche").get("P1").add(new Car("P12",123323,"ON"));
        this.modelsDB.get("Porsche").get("P1").add(new Car("P13",221231,"benzyna"));

        this.modelsDB.get("Porsche").put("P2", new ArrayList<Car>());
        this.modelsDB.get("Porsche").get("P2").add(new Car("P11",111222,"ON"));
        this.modelsDB.get("Porsche").get("P2").add(new Car("P12",432432,"ON"));
        this.modelsDB.get("Porsche").get("P2").add(new Car("P13",432212,"benzyna"));

        this.modelsDB.get("Porsche").put("P3", new ArrayList<Car>());
        this.modelsDB.get("Porsche").get("P3").add(new Car("P11",443432,"ON"));
        this.modelsDB.get("Porsche").get("P3").add(new Car("P12",123433,"ON"));
        this.modelsDB.get("Porsche").get("P3").add(new Car("P13",232444,"benzyna"));

        this.renderModelList = false;
        this.renderForm = false;
        this.renderOutput = false;
        this.notCorrectlyFilled = true;
        this.validNumber = false;
        this.validPrice = false;
        this.validName = false;
        this.carsList = new ArrayList<String>();
    }

    public String getChosenCarMake() {
        return chosenCarMake;
    }

    public void setChosenCarMake(String chosenCarMake) {
        this.chosenCarMake = chosenCarMake;
    }

    public Set<String> getModelsList() {
        return modelsList;
    }

    public String getChosenCarModel() {
        return chosenCarModel;
    }

    public void setChosenCarModel(String chosenCarModel) {
        this.chosenCarModel = chosenCarModel;
    }

    public ArrayList<String> getCarMakeList() {
        return carMakeList;
    }

    public void modelsListChanged(ValueChangeEvent e){

        //String chosenMake = (String) e.getOldValue();
        String chosenMake = (String) e.getNewValue();
        this.chosenCarMake = chosenMake;
        this.modelsList = this.modelsDB.get(this.chosenCarMake).keySet();
        this.renderModelList = true;
    }

    public void modelChosen(ValueChangeEvent e){
        this.chosenCarModel = (String) e.getNewValue();
        this.renderForm = true;
    }

    public void nameChosen(ValueChangeEvent e){
        this.clientName = (String) e.getNewValue();
        this.validName = true;
    }

    public void valueFromChanged(ValueChangeEvent e){
        this.from =Double.valueOf(e.getNewValue().toString());
    }

    public boolean isRenderModelList() {
        return renderModelList;
    }

    public boolean isRenderOutput() {
        return renderOutput;
    }

    public boolean isRenderForm() {
        return renderForm;
    }

    public boolean isCorrectlyFilled() {
        return notCorrectlyFilled;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public boolean isValidNumber() {
        return validNumber;
    }

    public void setValidNumber(boolean validNumber) {
        this.validNumber = validNumber;
    }

    public boolean isValidPrice() {
        return validPrice;
    }

    public void setValidPrice(boolean validPrice) {
        this.validPrice = validPrice;
    }

    public boolean isNotCorrectlyFilled() {
        return notCorrectlyFilled;
    }

    public void setNotCorrectlyFilled(boolean notCorrectlyFilled) {
        this.notCorrectlyFilled = notCorrectlyFilled;
    }

    public ArrayList<String> getCarsList() {
        return carsList;
    }

    public boolean isValidName() {
        return validName;
    }

    public void setValidName(boolean validName) {
        this.validName = validName;
    }

    public void processOutput(){

        this.carsList.clear();

        for(Car car : this.modelsDB.get(this.chosenCarMake).get(this.chosenCarModel)){

            Car correct = car.carChecker(this.from, this.to, this.engine);

            if(correct != null){
                String str = "";
                str += "Marka: " + this.chosenCarMake + " Model:" + this.chosenCarModel;
                str += "  Konkretny Model: " + correct.getID() + "  Cena: " + correct.getPrice();
                this.carsList.add(str);
            }
        }
        this.renderOutput = true;
    }

    public String clearForm(){
        this.notCorrectlyFilled = true;
        this.renderOutput = false;
        this.validName = false;
        this.from = 0.0;
        this.to = 0.0;
        this.engine = "";
        this.clientName = "";
        this.clientPhone = "";
        return "index";
    }
}
