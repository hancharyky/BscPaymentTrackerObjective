package bsc.example.objective.model;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/**
 * Bank account represents currencies and amounts which are person owns.
 *
 * @author Yahor
 */
public class BankAccount {

    private final Map<Currency, Payment> accounts;

    public BankAccount() {
        this.accounts = new HashMap<>();
    }

    public Map<Currency, Payment> getAccounts() {
        return accounts;
    }
}
