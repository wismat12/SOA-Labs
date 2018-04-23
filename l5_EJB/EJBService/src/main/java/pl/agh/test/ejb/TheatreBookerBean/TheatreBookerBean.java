package pl.agh.test.ejb.TheatreBookerBean;

import pl.agh.test.ejb.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.util.logging.Logger;

@Stateful
@Remote(TheatreBooker.class)
public class TheatreBookerBean implements TheatreBooker {

    private static final Logger logger =  Logger.getLogger(String.valueOf(TheatreBookerBean.class));
    int money;

    @EJB
    TheatreBox theatreBox;

    @PostConstruct
    public void createCustomer() {
        this.money=100;
    }

    @Override
    public String bookSeat(int seatId) throws SeatBookedException, NotEnoughMoneyException {

        Seat seat = theatreBox.getSeatList().get(seatId);
        // Logika biznesowa.

        if (seat.isBooked()) {
            throw new SeatBookedException("To miejsce jest już zarezerwowane!");
        }
        if (seat.getPrice() > money) {
            throw new NotEnoughMoneyException("Nie masz wystarczających środków, aby kupić ten bilet!");
        }
        theatreBox.buyTicket(seatId);
        money = money -seat.getPrice();
        logger.info("Rezerwacja przyjęta.");
        return "Rezerwacja przyjęta.";
    }
}