package bsc.example.objective.service;

import bsc.example.objective.model.BankAccount;
import bsc.example.objective.model.Payment;
import bsc.example.objective.repo.AccountRepo;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {

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
                System.out.println("Exception the payment substracts more than actual money available");

            } else {
                getAll(account).put(paymentCurrency, new Payment(paymentCurrency, newPaymentAmount));
                paymentSuccessful = true;
            }

        } else {
            getAll(account).put(paymentCurrency, new Payment(paymentCurrency, amount));
            paymentSuccessful = true;
        }

        return paymentSuccessful;
    }
}
