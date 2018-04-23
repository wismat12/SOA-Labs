package Car;

import java.util.ArrayList;
import java.util.HashMap;

public class CarChoiceHelper {

    public HashMap<String,ArrayList<Car>> DB;

    public CarChoiceHelper() {
        this.DB = new HashMap<>();
        this.DB.put("sportowy",new ArrayList<>());
        this.DB.put("miejski",new ArrayList<>());
        this.DB.put("luksusowy",new ArrayList<>());

        this.DB.get("sportowy").add(new Car("Ford Mustang", 55848.0));
        this.DB.get("sportowy").add(new Car("Dodge Challenger", 23454.0));
        this.DB.get("sportowy").add(new Car("Nissan 370Z Coupe", 11232.0));
        this.DB.get("sportowy").add(new Car("Chevrolet Corvette", 76543.0));
        this.DB.get("miejski").add(new Car("Kia Soul", 12342.0));
        this.DB.get("miejski").add(new Car("Toyota Prius c", 32123.0));
        this.DB.get("miejski").add(new Car("Lexus CT 200h", 23435.0));
        this.DB.get("miejski").add(new Car(" Buick Encore", 45555.0));
        this.DB.get("luksusowy").add(new Car("Mercedes-Benz GLA", 54323.0));
        this.DB.get("luksusowy").add(new Car("Volvo S60", 66543.0));
        this.DB.get("luksusowy").add(new Car("INFINITI Q50", 74432.0));
        this.DB.get("luksusowy").add(new Car("Tesla Model III", 56432.0));
    }
}
