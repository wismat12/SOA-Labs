package pl.agh.soa.webservice;

import javax.jws.WebService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebService(endpointInterface="pl.agh.soa.webservice.TimeToHoliday")
public class TimeToHolidayImpl implements TimeToHoliday {
    @Override
    public String getTimeToHoliday() {

        long daysElapsed;

        LocalDate today = LocalDate.now();
        int yearAtNow = today.getYear();

        LocalDate holidayDate = LocalDate.parse(Integer.toString(yearAtNow) + "-07-01");
        LocalDate holidayDate2 = LocalDate.parse(Integer.toString(yearAtNow + 1) + "-07-01");

        if (today.isBefore(holidayDate)) {

            daysElapsed = ChronoUnit.DAYS.between(today, holidayDate);

        } else {

            daysElapsed = ChronoUnit.DAYS.between(today, holidayDate2);

        }
        return String.valueOf(daysElapsed);
    }
}
