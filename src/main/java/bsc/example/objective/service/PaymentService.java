package bsc.example.objective.service;

import bsc.example.objective.model.BankAccount;
import bsc.example.objective.model.Payment;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

/**
 * Service handles payments logic
 *
 * @author Yahor
 */
public interface PaymentService {

    /**
     * Method returns all user payments from his account
     *
     * @return Map where key is a currency of payments, value is the payment
     */
    Map<Currency, Payment> getAll(BankAccount account);

    /**
     * Method for payment retrieving
     *
     * @param currency denotes payment currency
     * @return payment with corresponding currency
     */
    Payment getPayment(BankAccount account, Currency currency);


    /**
     * Persist payment amount in specified currency to a bank account
     *
     * @param account user bank account
     * @param currency payment currency
     * @param amount payment amount
     * @return
     */
    boolean makePayment(BankAccount account, String currency, BigDecimal amount);
}
