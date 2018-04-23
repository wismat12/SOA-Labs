package com.example.faces;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Szansa {
    public String wyslij() {
        if (Math.random() < 0.2 ) {
            return "wygrana";
        } else {
            return "przegrana";
        }
    }
}