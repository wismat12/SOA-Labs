package pl.agh.soa.webservice;

import javax.xml.ws.Endpoint;

public class TimeToHolidayPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/TimeToHoliday/TimeToHoliday",new TimeToHolidayImpl());
        System.out.println("published");
    }
}
