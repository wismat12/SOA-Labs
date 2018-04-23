package pl.agh.test.ejb.TheatreInfoBean;

import pl.agh.test.ejb.Seat;
import pl.agh.test.ejb.TheatreBox;
import pl.agh.test.ejb.TheatreInfo;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
@Remote(TheatreInfo.class)
public class TheatreInfoBean implements TheatreInfo {

    @EJB
    TheatreBox box;

    @Override
    public String printSeatList() {
        ArrayList<Seat> seats= box.getSeatList();
        StringBuffer sb = new StringBuffer();
        for (Seat seat: seats) {
            sb.append(seat.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}