package bsc.example.objective;

import bsc.example.objective.service.BankAccountServiceImpl;
import bsc.example.objective.service.InputProcessorServiceImpl;
import bsc.example.objective.service.OutputProcessorServiceImpl;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        BankAccountServiceImpl bankAccountService = new BankAccountServiceImpl();
        InputProcessorServiceImpl inputProcessorService = new InputProcessorServiceImpl(bankAccountService);
        OutputProcessorServiceImpl outputProcessorService = new OutputProcessorServiceImpl(bankAccountService);

        String filename = "payments.txt";
        String exchangeRates = "rates.txt";
        BufferedReader bufferedFileReader = new BufferedReader(new FileReader(filename));
        //BufferedReader bufferedRatesReader = new BufferedReader(new FileReader(exchangeRates));
        BufferedReader bufferedConsoleReader = new BufferedReader(new InputStreamReader(System.in));
        inputProcessorService.processInput(bufferedFileReader,"FILE");
        //inputProcessorService.processInput(bufferedRatesReader,"RATES");
        System.out.println("Welcome to you payment tracker program! To exit, enter \"quit\"\n" +
                "If you want to proceed, please, enter payment currency code and amount:");

        ExecutorService myExecutor = Executors.newCachedThreadPool();

        myExecutor.execute(new Runnable() {
           public void run() {
               try {
                   while (true) {
                       Thread.sleep(5 * 1000);
                       outputProcessorService.printOut();
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       });


        inputProcessorService.processInput(bufferedConsoleReader,"CONSOLE");


    }
}
