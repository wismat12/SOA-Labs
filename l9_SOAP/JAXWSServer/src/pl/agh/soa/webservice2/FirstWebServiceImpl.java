package pl.agh.soa.webservice2;

import javax.jws.WebService;
import java.util.HashMap;

@WebService(endpointInterface="pl.agh.soa.webservice2.FirstWebService")
public class FirstWebServiceImpl implements FirstWebService {

    @Override
    public HashMap<String,String> addTwoNumbers(int firstNumber, int secondNumber) {

        HashMap<String,String> tmp = new HashMap<>();

        tmp.put("pierwsza","1");
        tmp.put("druga","2");

        return tmp;

    }
}
