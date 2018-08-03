package bsc.example.objective.main;

import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.service.BankAccountServiceImpl;
import bsc.example.objective.service.InputProcessorServiceImpl;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * @author Yahor
 */
public class PaymentTracker {

    AccountRepo accountRepo;
    BankAccountServiceImpl bankAccountService;
    InputProcessorServiceImpl inputProcessorService;

    String filename = "payments.txt";

    public PaymentTracker() {
        this.accountRepo = new AccountRepo();
        this.bankAccountService = new BankAccountServiceImpl(accountRepo);

        this.inputProcessorService =  new InputProcessorServiceImpl(bankAccountService);
    }

    public void init(){

        try {
            inputProcessorService.processInput(new FileReader(filename));
            outputAccount();
            System.out.println("Welcome to you payment tracker program! To exit, enter \"quit\"\n" +
                    "If you want to proceed, please, enter payment currency code and amount:");
            inputProcessorService.processInput(new InputStreamReader(System.in));

        } catch (IOException | IllegalArgumentException e) {
            System.out.println("There is severe problem with your payment input. Please contact customer service.");
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
                    e.printStackTrace();
                }
            }
        });
    }

}
