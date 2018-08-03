package bsc.example.objective.repo;

import bsc.example.objective.model.BankAccount;
import bsc.example.objective.model.Payment;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

/**
 * Repository which return BankAccount objects.
 *
 * @link BankAccount
 *
 * @author Yahor
 */
public class AccountRepo {

    private BankAccount bankAccount;

    public AccountRepo() {
        bankAccount = new BankAccount();
    }

    public BankAccount getBankAccount(){
        return bankAccount;
    }

}
