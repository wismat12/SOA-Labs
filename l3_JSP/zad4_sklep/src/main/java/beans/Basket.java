package beans;

import java.io.Serializable;
import java.util.HashMap;

public class Basket implements Serializable{

                    //ID    ilosc
    private HashMap<String,Integer> userBasket;

    public Basket() {
        this.userBasket = new HashMap<String, Integer>();
    }

    public void addProduct(String id){
        if(this.userBasket.get(id)!=null){
            this.userBasket.replace(id,this.userBasket.get(id) + 1);
        }else{
            this.userBasket.put(id,1);
        }
    }

    public void deleteProduct(String id){
        this.userBasket.remove(id);
    }

    public int getProductAmount(String id){
        return this.userBasket.get(id);
    }

    public HashMap<String, Integer> getUserBasket() {
        return userBasket;
    }
}
