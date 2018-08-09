package bsc.example.service;

import bsc.example.objective.model.BankAccount;
import bsc.example.objective.service.PaymentService;
import bsc.example.objective.service.PaymentServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Yahor
 */
public class PaymentServiceTest {

    String currencyCode;
    Currency currency;
    BigDecimal amount;
    private BankAccount bankAccount;
    private PaymentService paymentService;

    @Before
    public void init(){
        this.currencyCode = "CZK";
        this.currency = Currency.getInstance(currencyCode);
        this.amount = new BigDecimal(10_000L);
        this.bankAccount = new BankAccount();
        this.paymentService = new PaymentServiceImpl();
    }

    @Test
    public void getAllPayments(){
        Assert.assertNotNull(paymentService.getAll(bankAccount));
    }

    @Test
    public void getPayment(){
        Assert.assertNotNull(paymentService.getPayment(bankAccount, currency));
    }

    @Test
    public void makePaymentTest(){
        Assert.assertNull(bankAccount.getAccounts().get(currency));
        paymentService.makePayment(bankAccount, currencyCode, amount);
        Assert.assertNotNull(bankAccount.getAccounts().get(currency).getAmount());
        Assert.assertTrue(bankAccount.getAccounts().get(currency).getAmount().equals(amount));

    }


}
