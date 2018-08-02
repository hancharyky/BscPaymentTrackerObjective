package bsc.example.objective.service;

import bsc.example.objective.model.Payment;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public interface BankAccountService {

    Map<Currency, Payment> getBankAccount();
    void transaction(String inputCurrency, BigDecimal amount);
}
