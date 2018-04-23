package com.example.faces;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Map;

@ManagedBean
public class Losowanie {

    static int losuj = (int)(Math.random() * 5 + 1);

    public Losowanie() {
    }

    public String przejdz() {
        //getting param from jsf
        System.out.println(losuj);
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

        if(params.get("toPage").equals(Integer.toString(losuj))){
            return "trafiony";
        }else{
            return params.get("toPage");
        }
    }
    public String losujZnowu(){
        losuj = (int)(Math.random() * 5 + 1);
        return "indexStrony";
    }
}
