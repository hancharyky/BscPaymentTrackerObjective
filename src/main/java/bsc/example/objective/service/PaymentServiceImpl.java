package bsc.example.objective.service;

import bsc.example.objective.main.PaymentTracker;
import bsc.example.objective.model.BankAccount;
import bsc.example.objective.model.Payment;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;


/**
 * @author Yahor
 */
public class PaymentServiceImpl implements PaymentService {
    private final static Logger log = Logger.getLogger(PaymentTracker.class);

    @Override
    public Map<Currency, Payment> getAll(BankAccount account) {
        return account.getAccounts();
    }

    @Override
    public Payment getPayment(BankAccount account, Currency currency) {
        return getAll(account).get(currency);
    }

    @Override
    public boolean makePayment(BankAccount account, String currency, BigDecimal amount) {
        boolean paymentSuccessful = false;
        Currency paymentCurrency = Currency.getInstance(currency);

        Payment actualPayment = this.getPayment(account, paymentCurrency);

        if(actualPayment != null){
            BigDecimal newPaymentAmount = actualPayment.getAmount().add(amount);

            if(newPaymentAmount.compareTo(BigDecimal.ZERO) < 0){
                System.out.println("Exception the payment substructs more than actual money available");

            } else {
                getAll(account).put(paymentCurrency, new Payment(paymentCurrency, newPaymentAmount));
                paymentSuccessful = true;
            }

        } else {
            getAll(account).put(paymentCurrency, new Payment(paymentCurrency, amount));
            paymentSuccessful = true;
        }

        log.info(String.format("Payment %s %s succeed: %b", currency, amount.toString(), paymentSuccessful));

        return paymentSuccessful;
    }
}
