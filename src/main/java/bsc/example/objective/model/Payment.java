package bsc.example.objective.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Class is a currency account which represented by actual currency code, amount and corresponding value in USD.
 *
 * @author Yahor
 */
public class Payment {
    private Currency code;
    private BigDecimal amount;

    public Payment(Currency code, BigDecimal amount) {
        this.code = code;
        this.amount = amount;
    }

    public Currency getCode() {
        return code;
    }

    public void setCode(Currency code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return code + " " +amount;
    }
}
