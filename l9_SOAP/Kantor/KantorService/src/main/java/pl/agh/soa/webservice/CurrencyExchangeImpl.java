package pl.agh.soa.webservice;

import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@WebService(endpointInterface="pl.agh.soa.webservice.CurrencyExchange")
public class CurrencyExchangeImpl implements CurrencyExchange{

    public String getRate(String currency) {

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

                    div = Double.valueOf(line.substring(4,line.indexOf(currency.toUpperCase())));
                    line = br.readLine();
                    answer = line.substring(4,line.indexOf("</td>"));
                    break;
                }
            }
            if (!answer.equals("")) {
                answer = answer.replace(answer.charAt(answer.indexOf(",")), '.');
                return BigDecimal.valueOf(Double.valueOf(answer) / div).toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Brak "+currency+" tej waluty!";
    }

    public String sell(String currency, String amount) {
        String output = getRate(currency);

        try{
            Double rate = Double.valueOf(output);

            return String.valueOf(rate * Double.valueOf(amount));

        }catch (Exception e){
            return "Brak "+currency+" tej waluty!";
        }
    }
}
