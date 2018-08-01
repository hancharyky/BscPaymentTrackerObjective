package bsc.example;

import java.math.BigDecimal;

/**
 * @author Yahor
 */
public class CurrencyExchange {
    private String currencyCode;
    private BigDecimal currencyAmount;
    private String exCurCode;
    private BigDecimal exCurAmount;

    public CurrencyExchange(String currencyCode, BigDecimal currencyAmount, String exCurCode, BigDecimal exCurAmount) {
        this.currencyCode = currencyCode;
        this.currencyAmount = currencyAmount;
        this.exCurCode = exCurCode;
        this.exCurAmount = exCurAmount;
    }

    public String getConverted(){
        return currencyCode + " " + currencyAmount + " (" + exCurCode + " " + exCurAmount + ")";
    }

}
