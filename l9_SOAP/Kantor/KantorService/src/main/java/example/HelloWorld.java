package example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class HelloWorld {

  public static void main(String[] argv) {

   String currency = "HUF";
    try {

      Double div = 1.0;

      String answer ="";
      URL url = new URL("https://kursy-walut.mybank.pl/");

      URLConnection con = url.openConnection();
      InputStream is =con.getInputStream();

      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String line = null;

      while ((line = br.readLine()) != null) {

        if (line.matches("<td>.*"+currency.toUpperCase()+".*")) {

          System.out.println(line.substring(4,line.indexOf(currency.toUpperCase())));
          div = Double.valueOf(line.substring(4,line.indexOf(currency.toUpperCase())));
          line = br.readLine();
          answer = line.substring(4,line.indexOf("</td>"));
          break;

        }
      }
      if (!answer.equals("")) {
        answer = answer.replace(answer.charAt(answer.indexOf(",")), '.');
        System.out.println(BigDecimal.valueOf(Double.valueOf(answer) / div).toString());
      }

    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("Brak "+currency+" tej waluty!");


  }
}
