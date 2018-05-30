package example;


import pl.agh.webservice2.AddTwoNumbersResponse;
import pl.agh.webservice2.FirstWebService;
import pl.agh.webservice2.FirstWebServiceImplService;

import java.util.HashMap;

public class HelloWorldClient {




  public static void main(String[] argv) {
      FirstWebService service = new FirstWebServiceImplService().getFirstWebServiceImplPort();
      //invoke business method
      AddTwoNumbersResponse.Return object = service.addTwoNumbers(1,3);

      for(AddTwoNumbersResponse.Return.Entry entry : object.getEntry()){
          System.out.println("Klucz " + entry.getKey() + " Wskazuje na: " + entry.getValue());
      }
  }
}
