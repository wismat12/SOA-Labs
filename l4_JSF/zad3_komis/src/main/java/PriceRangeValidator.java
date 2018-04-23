
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("PriceRangeValidator")
public class PriceRangeValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Object containerBean = component.getAttributes().get("shopBean");

        if(containerBean instanceof CarShop) {

            try {

                double from = ((CarShop) containerBean).getFrom();

                double to = Double.valueOf(value.toString());


            } catch (Exception e) {
                ((CarShop) containerBean).setValidPrice(false);
                FacesMessage msg =
                        new FacesMessage("Price range validation failed","Invalid Price input");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

            double from = ((CarShop) containerBean).getFrom();
            double to = Double.valueOf(value.toString());

            if(from>to){

                ((CarShop) containerBean).setValidPrice(false);
                FacesMessage msg =
                        new FacesMessage("Price range validation failed.",
                                "To cannot be smaller than from");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }

            ((CarShop) containerBean).setValidPrice(true);
            if((((CarShop) containerBean).isValidNumber())&&(((CarShop) containerBean).isValidName())){
                ((CarShop) containerBean).setNotCorrectlyFilled(false);
            }
        }
    }
}

