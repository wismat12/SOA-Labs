public class Car {

    private String ID;
    private double price;
    private String engine;

    public Car(String ID, double price, String engine) {
        this.ID = ID;
        this.price = price;
        this.engine = engine;
    }
    public Car carChecker(double from, double to, String eng){
        if((this.price<= to)&&(this.price>=from)){
            if(this.engine.equals(eng))
                return this;
        }
        return null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
