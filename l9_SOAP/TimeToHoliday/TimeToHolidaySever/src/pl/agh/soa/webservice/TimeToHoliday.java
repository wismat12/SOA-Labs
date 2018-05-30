package pl.agh.soa.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TimeToHoliday {

    @WebMethod
    public  String getTimeToHoliday();
}
