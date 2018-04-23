import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("PhoneNumberValidator")
public class PhoneNumberValidator implements Validator {

    private static final String PHONE_PATTERN = "^[0-9]{9}$";

    private Pattern pattern;
    private Matcher matcher;

    public PhoneNumberValidator(){
        pattern = Pattern.compile(PHONE_PATTERN);
    }

    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

        Object containerBean = component.getAttributes().get("shopBean");

        if(containerBean instanceof CarShop) {

            if(!value.toString().equals("")){

                matcher = pattern.matcher(value.toString());
                if(!matcher.matches()){

                    ((CarShop) containerBean).setValidNumber(false);

                    FacesMessage msg =
                            new FacesMessage("Phone Number validation failed.",
                                    "Invalid Phone Number format.");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    throw new ValidatorException(msg);
                }

                ((CarShop) containerBean).setValidNumber(true);
                if((((CarShop) containerBean).isValidPrice())&&(((CarShop) containerBean).isValidName())){
                    ((CarShop) containerBean).setNotCorrectlyFilled(false);
                }
            }
        }

    }
}