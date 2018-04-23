package beans;

import java.io.Serializable;

public class NumberToGuess implements Serializable {

    private int number;

    public NumberToGuess() {
        this.number = (int)(Math.random() * 101);
    }

    public int getNumber() {
        return number;
    }
}
