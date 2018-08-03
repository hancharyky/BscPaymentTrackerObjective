package bsc.example.objective.service;

import java.math.BigDecimal;


/**
 * Service for working with bank account
 *
 * @link BankAccount
 *
 * @author Yahor
 */
public interface BankAccountService {

    /**
     * Method implements logic of payment addition. All parameters are pre-validated.
     *
     * @param paymentCurrency is String which denotes payment currency
     * @param amount is a BigDecimal payment value
     * @return
     */
    boolean addPayment(String paymentCurrency, BigDecimal amount);

    void printBalance();
}
