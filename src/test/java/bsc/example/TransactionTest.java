package bsc.example;

import bsc.example.objective.service.BankAccountServiceImpl;
import bsc.example.objective.service.InputProcessorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

/**
 * @author Yahor
 */
public class TransactionTest {
    BankAccountServiceImpl bankAccountService;
    InputProcessorServiceImpl inputProcessorService;

    @Before
    public void init(){
        bankAccountService = new BankAccountServiceImpl();
        inputProcessorService = new InputProcessorServiceImpl(bankAccountService);
    }

    @Test
    public void testTransaction(){
        ByteArrayInputStream in = new ByteArrayInputStream("USD 100".getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        try {
            inputProcessorService.processInput(br, "CONSOLE");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BigDecimal moneyInAccount = bankAccountService.getBankAccount().get(Currency.getInstance("USD")).getAmount();

        assertEquals(new BigDecimal(100L), moneyInAccount);

    }

}
