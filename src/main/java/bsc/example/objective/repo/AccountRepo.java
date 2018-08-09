package bsc.example.objective.repo;

import bsc.example.objective.model.BankAccount;

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
