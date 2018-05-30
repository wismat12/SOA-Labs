package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import pl.agh.soa.webservice.*;

@ManagedBean(eager = true)
@SessionScoped
public class CurrencyExchangeBean implements Serializable{

    private CurrencyExchange service;

    private String currency = "";
    private String amount= "";
    private String rateOutput= "";
    private String amountoutput= "";

    public CurrencyExchangeBean() {

        this.service = new CurrencyExchangeImplService().getCurrencyExchangeImplPort();
    }

    public void askForRate(){
        this.rateOutput = this.service.getRate(currency);
    }

    public void askForSelling(){
        this.amountoutput = this.service.sell(currency,amount);
        askForRate();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRateOutput() {
        return rateOutput;
    }

    public void setRateOutput(String rateOutput) {
        this.rateOutput = rateOutput;
    }

    public String getAmountoutput() {
        return amountoutput;
    }

    public void setAmountoutput(String amountoutput) {
        this.amountoutput = amountoutput;
    }
}
