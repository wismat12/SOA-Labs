package pl.agh.test.ejb;

public class Seat {

    private int id;
    private String seatName;
    private  int price;
    private boolean booked;

    public Seat(int id, String seat, int price) {
        this.id = id;
        this.seatName = seat;
        this.price = price;
        this.booked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}