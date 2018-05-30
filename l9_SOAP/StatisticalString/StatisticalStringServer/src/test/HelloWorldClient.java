package test;

import pl.agh.soa.webservice.string.*;

import javax.xml.ws.Endpoint;

public class HelloWorldClient {


  public static void main(String[] argv) {

      Endpoint.publish("http://localhost:8081/StatisticalStringServer/StatisticalStringImpl",new StatisticalStringImpl());

      System.out.println("published");

  }
}
