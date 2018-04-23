package pl.agh.converter.ejb.ConverterBean;

import pl.agh.converter.ejb.Converter;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
@Remote(Converter.class)
public class ConverterBean implements Converter {

    private static final Logger logger = Logger.getLogger(String.valueOf(Converter.class));

    @Override
    public double Fahr2Cels(double temp) {
        logger.info("Fahr2Cels hey111 komunikat z EJB");
        logger.info("Zamieniana wartosc F : " + String.valueOf(temp));
        return 5.0/9.0 * (temp - 32.0);
    }

    @Override
    public double CEls2Fahr(double temp) {
        logger.info("CEls2Fahr hey222 komunikat z EJB");
        logger.info("zamieniana wartosc C : " + String.valueOf(temp));
        return 9.0/5.0 * temp + 32.0;
    }
}
