package beans;

import java.io.Serializable;

public class Counter implements Serializable {

    private int counterNumber;

    public Counter() {
        this.counterNumber = 0;
    }

    public int getCounterNumber() {
        return counterNumber;
    }

    public void setCounterNumber(int counterNumber) {
        this.counterNumber = counterNumber;
    }

    public void increment(){
        this.counterNumber++;
    }
}
