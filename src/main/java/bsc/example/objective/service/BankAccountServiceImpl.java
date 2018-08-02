package bsc.example.objective.service;

import bsc.example.objective.model.Payment;
import bsc.example.objective.model.BankAccount;

import java.math.BigDecimal;
import java.util.Currency;

import java.util.Map;

/**
 * Contains useful methods for working with  user's bank account which contains multiple currency accounts.
 *
 * @author Yahor
 */
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccount bankAccount;

    public BankAccountServiceImpl() {
        bankAccount = new BankAccount();
    }

    @Override
    public Map<Currency, Payment> getBankAccount() {
        return bankAccount.getAccounts();
    }

    public void transaction(String inputCurrency, BigDecimal amount){
        Currency currency = Currency.getInstance(inputCurrency);

        Payment actualPayment = getBankAccount().get(currency);

        if(actualPayment != null){
            BigDecimal newPaymentAmount = actualPayment.getAmount().add(amount);

            if(newPaymentAmount.compareTo(BigDecimal.ZERO) < 0){
                System.out.println("Exception the payment substracts more than actual money available");
            } else {
                getBankAccount().put(currency, new Payment(currency, newPaymentAmount));
            }

        } else {
            getBankAccount().put(currency, new Payment(currency, amount));
        }

    }
}
