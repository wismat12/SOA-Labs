package pl.agh.test.ejb;

public interface TheatreBooker {

    public String bookSeat(int seatId) throws SeatBookedException, NotEnoughMoneyException;
}
