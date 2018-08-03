package bsc.example;

import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.service.BankAccountServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class BankServiceTest {
    BankAccountServiceImpl bankAccountService;

    @Before
    public void init() {
        AccountRepo accountRepo = new AccountRepo();
        bankAccountService = new BankAccountServiceImpl(accountRepo);
    }

    @Test
    public void bankSuccessfulPayment(){
        assertTrue(bankAccountService.addPayment("USD", new BigDecimal(1000L)));
    }

}
