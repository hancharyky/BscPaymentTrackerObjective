package bsc.example.objective.main;

import bsc.example.objective.exception.InvalidInputException;
import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.service.BankAccountServiceImpl;
import bsc.example.objective.service.InputProcessorServiceImpl;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * @author Yahor
 */
public class PaymentTracker {

    final static Logger log = Logger.getLogger(PaymentTracker.class);
    AccountRepo accountRepo;
    BankAccountServiceImpl bankAccountService;
    InputProcessorServiceImpl inputProcessorService;

    String filename = "paymentss.txt";

    public PaymentTracker() {
        this.accountRepo = new AccountRepo();
        this.bankAccountService = new BankAccountServiceImpl(accountRepo);
        this.inputProcessorService =  new InputProcessorServiceImpl(bankAccountService);
    }

    public void init(){
        try {
            inputProcessorService.processInput(new FileReader(filename));
        } catch (InvalidInputException | FileNotFoundException e) {
            log.error("File " + filename + " is erroneous or doesn't exist", e);
            System.out.println("There is a problem with input file please contact customer service.");
            System.exit(-1);
        }

        outputAccount();
            System.out.println("Welcome to you payment tracker program! To exit, enter \"quit\"\n" +
                    "If you want to proceed, please, enter payment currency code and amount:");
        try {
            inputProcessorService.processInput(new InputStreamReader(System.in));
        } catch (InvalidInputException e) {
            log.error("User input " + System.in + " is erroneous", e);
        }
    }

    private void outputAccount(){
        ExecutorService myExecutor = Executors.newCachedThreadPool();
        myExecutor.execute(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(60 * 1000);
                        bankAccountService.printBalance();
                    }
                } catch (InterruptedException e) {
                    log.error(e);
                }
            }
        });
    }

}
