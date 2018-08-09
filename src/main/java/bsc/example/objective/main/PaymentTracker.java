package bsc.example.objective.main;

import bsc.example.objective.exception.InvalidUserInputException;
import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.service.BankAccountServiceImpl;
import bsc.example.objective.service.InputProcessorServiceImpl;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Start program for payment tracker application.
 *
 * @author Yahor
 */
public class PaymentTracker {

    private final static Logger log = Logger.getLogger(PaymentTracker.class);

    AccountRepo accountRepo;
    BankAccountServiceImpl bankAccountService;
    InputProcessorServiceImpl inputProcessorService;
    ExecutorService executor;

    String filename = "payments.txt";

    public PaymentTracker() {
        this.accountRepo = new AccountRepo();
        this.bankAccountService = new BankAccountServiceImpl(accountRepo);
        this.inputProcessorService =  new InputProcessorServiceImpl(bankAccountService);
        this.executor = Executors.newCachedThreadPool();
    }

    public void init(){
        try {
            inputProcessorService.processInput(new FileReader(filename));
        } catch (InvalidUserInputException | FileNotFoundException e) {
            log.error("File " + filename + " is erroneous or doesn't exist", e);
            System.out.println("There is a problem with input file please contact customer service.");
            System.exit(-1);
        }

        outputAccount();
            System.out.println("Welcome to you payment tracker program! To exit, enter \"quit\"\n" +
                    "If you want to proceed, please, enter payment currency code and amount:");
        try {
            inputProcessorService.processInput(new InputStreamReader(System.in));
        } catch (InvalidUserInputException e) {
            log.error("User input " + System.in + " is erroneous", e);
        }
    }


    /**
     * Method outputs the state of user account one tame at a minute.
     */
    private void outputAccount(){
        executor.execute(new Runnable() {
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

    /**
     *  Method shutdowns all current running executor tasks.
     */
    public void shutdownExecutor(){
        executor.shutdownNow();
    }


}
