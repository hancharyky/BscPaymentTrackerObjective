package bsc.example.service;

import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.service.BankAccountServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * @author Yahor
 */
public class BankServiceTest {
    BankAccountServiceImpl bankAccountService;

    @Before
    public void init() {
        AccountRepo accountRepo = new AccountRepo();
        bankAccountService = new BankAccountServiceImpl(accountRepo);
    }

    @Test
    public void bankService_successfulPayment(){
        assertTrue(bankAccountService.addPayment("USD", new BigDecimal(1000L)));
    }

}
