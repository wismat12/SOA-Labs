package pl.agh.soa.webservice.string;

import javax.jws.WebService;


@WebService(endpointInterface="pl.agh.soa.webservice.string.StatisticalString")
public class StatisticalStringImpl implements StatisticalString {
    @Override
    public AnalyseStringResponse.ReturnedHashMap analyseString(String yourString) {

        AnalyseStringResponse.ReturnedHashMap tmp = new AnalyseStringResponse.ReturnedHashMap();

        tmp.getEntry().add(0, new AnalyseStringResponse.ReturnedHashMap.Entry());
        tmp.getEntry().add(1, new AnalyseStringResponse.ReturnedHashMap.Entry());
        tmp.getEntry().add(2, new AnalyseStringResponse.ReturnedHashMap.Entry());
        tmp.getEntry().add(3, new AnalyseStringResponse.ReturnedHashMap.Entry());
        tmp.getEntry().add(4, new AnalyseStringResponse.ReturnedHashMap.Entry());

        tmp.getEntry().get(0).setKey("charsAmount");
        tmp.getEntry().get(1).setKey("whiteCharsAmount");
        tmp.getEntry().get(2).setKey("charsUpperAmount");
        tmp.getEntry().get(3).setKey("charsLowerAmount");
        tmp.getEntry().get(4).setKey("charsDigitsAmount");

        int length = yourString.length(), whitespaces = 0, uppercase = 0, lowercase = 0, digits = 0;

        for (Character c : yourString.toCharArray()) {
            if (Character.isDigit(c))
                digits++;
            if (Character.isUpperCase(c))
                uppercase++;
            if (Character.isLowerCase(c))
                lowercase++;
            if (Character.isWhitespace(c))
                whitespaces++;
        }


        tmp.getEntry().get(0).setValue(String.valueOf(length));
        tmp.getEntry().get(1).setValue(String.valueOf(whitespaces));
        tmp.getEntry().get(2).setValue(String.valueOf(uppercase));
        tmp.getEntry().get(3).setValue(String.valueOf(lowercase));
        tmp.getEntry().get(4).setValue(String.valueOf(digits));

        return tmp;
    }
}
