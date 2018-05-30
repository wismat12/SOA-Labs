package pl.agh.soa.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CurrencyExchange {

    @WebMethod
    public String getRate(String currency);

    @WebMethod
    public String sell(String currency, String amount );

}
