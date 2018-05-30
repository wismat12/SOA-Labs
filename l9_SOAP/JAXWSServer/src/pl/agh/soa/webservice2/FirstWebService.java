package pl.agh.soa.webservice2;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.HashMap;

@WebService
public interface FirstWebService {

    @WebMethod
    public HashMap<String,String> addTwoNumbers(int firstNumber, int secondNumber);

}




