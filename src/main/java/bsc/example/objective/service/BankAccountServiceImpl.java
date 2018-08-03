package bsc.example.objective.service;

import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.model.Payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;
import java.util.Map;

public class BankAccountServiceImpl implements BankAccountService{

    private AccountRepo accountRepo;

    public BankAccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public boolean addPayment(String paymentCurrency, BigDecimal amount){
        boolean paymentSuccessful = false;
        Currency currency = Currency.getInstance(paymentCurrency);
        Map<Currency, Payment> userAccounts = accountRepo.getBankAccount().getAccounts();

        Payment actualPayment = userAccounts.get(currency);

        if(actualPayment != null){
            BigDecimal newPaymentAmount = actualPayment.getAmount().add(amount);

            if(newPaymentAmount.compareTo(BigDecimal.ZERO) < 0){
                System.out.println("Exception the payment substracts more than actual money available");

            } else {
                userAccounts.put(currency, new Payment(currency, newPaymentAmount));
                paymentSuccessful = true;
            }

        } else {
            userAccounts.put(currency, new Payment(currency, amount));
            paymentSuccessful = true;
        }

        return paymentSuccessful;
    }

    @Override
    public void printBalance() {
        Collection<Payment> myAccount = accountRepo.getBankAccount().getAccounts().values();
        myAccount.stream().filter(x -> !x.getAmount().equals(BigDecimal.ZERO)).forEach(System.out::println);
    }


}
