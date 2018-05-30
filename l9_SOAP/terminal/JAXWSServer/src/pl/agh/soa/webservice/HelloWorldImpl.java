package pl.agh.soa.webservice;


import javax.jws.WebService;

@WebService(endpointInterface="pl.agh.soa.webservice.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String helloWorld(String name) {
        return "Pozdrowienia od " + name;
    }
}
