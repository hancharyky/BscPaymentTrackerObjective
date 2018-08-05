package bsc.example.objective.service;


import bsc.example.objective.util.ValidationUtil;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
 * Process user input and pass it to transact it into bank account.
 *
 * @author Yahor
 */
public class InputProcessorServiceImpl implements InputProcessorService {
    private final Logger log = Logger.getLogger(InputProcessorServiceImpl.class);

    BankAccountServiceImpl bankAccountService;
    private final String EXIT_COMMAND = "quit";
    private final int NORMAL_EXIT = 0;

    public InputProcessorServiceImpl(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void processInput(InputStreamReader consoleReader){
        try(BufferedReader bufferedConsoleReader = new BufferedReader(consoleReader)){

            String line;

            while ((line = bufferedConsoleReader.readLine()) != null){
                if(EXIT_COMMAND.equalsIgnoreCase(line)){
                    System.exit(NORMAL_EXIT);
                }

                if (ValidationUtil.isValid(line)) {
                    processInputLine(line);
                } else {
                    System.out.println("Input is invalid, please enter a payment in form \"USD 100.10\"");
                }
            }
        } catch (IOException e) {
            log.error(e);
        }

    }

    @Override
    public void processInput(FileReader fileReader)  {
        try(BufferedReader bufferedFileReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedFileReader.readLine()) != null) {
                if (ValidationUtil.isValid(line)) {
                    processInputLine(line);
                } else {
                    throw new IllegalArgumentException("Input file contains invalid data.");
                }
            }
        } catch (IOException e) {
            System.out.println("There is a problem. Please, contact the customer service");
            log.error(e);
        } catch (IllegalArgumentException e){
            System.out.println("Input payments file contains invalid data");
            log.error("Input file contains invalid data", e);
        }

    }


    private void processInputLine(String line){
        String[] parsedPayment = line.split(" ");

        String currency = parsedPayment[0];
        BigDecimal amount = new BigDecimal(parsedPayment[1]);

        bankAccountService.addPayment(currency, amount);


    }


}
