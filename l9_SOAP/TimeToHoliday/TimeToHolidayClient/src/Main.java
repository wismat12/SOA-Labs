import pl.agh.soa.webservice.*;

public class Main {

    public static void main(String[] args) {

        TimeToHoliday service = new TimeToHolidayImplService().getTimeToHolidayImplPort();

        System.out.println("Ilosc dni do wakacji " + service.getTimeToHoliday());
    }
}
