package bsc.example;

import bsc.example.objective.model.BankAccount;
import bsc.example.objective.repo.AccountRepo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class BankAccountRepoTest {
    private AccountRepo accountRepo;

    @Before
    public void init(){
        accountRepo = new AccountRepo();
    }

    @Test
    public void getBankAccount(){
        assertTrue(accountRepo.getBankAccount() instanceof BankAccount);
    }
}
