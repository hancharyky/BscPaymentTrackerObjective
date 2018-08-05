package bsc.example.objective.service;

import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.model.Payment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

public class BankAccountServiceImpl implements BankAccountService{

    private AccountRepo accountRepo;
    private PaymentService paymentService;

    public BankAccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
        this.paymentService = new PaymentServiceImpl();
    }

    @Override
    public boolean addPayment(String paymentCurrency, BigDecimal amount){
        return paymentService.makePayment(accountRepo.getBankAccount(), paymentCurrency, amount);
    }

    @Override
    public void printBalance() {
        Collection<Payment> myAccount = paymentService.getAll(accountRepo.getBankAccount()).values();
        myAccount.stream().filter(x -> !x.getAmount().equals(BigDecimal.ZERO)).forEach(System.out::println);
    }


}
