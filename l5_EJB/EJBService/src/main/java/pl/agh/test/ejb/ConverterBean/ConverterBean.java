package pl.agh.test.ejb.ConverterBean;


import pl.agh.test.ejb.Converter;
import pl.agh.test.ejb.TheatreBox;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.logging.Logger;

@Stateless
@Remote(Converter.class)
public class ConverterBean implements Converter {

    private static final Logger logger = Logger.getLogger(String.valueOf(Converter.class));

    @Override
    public double Fahr2Cels(double temp) {
        logger.info("Fahr2Cels hey222");
        logger.info(String.valueOf(temp));
        return ((5.0/9.0) * (temp - 32.0));
    }

    @Override
    public double CEls2Fahr(double temp) {
        return 9.0/5.0 * temp + 32.0;
    }
}
