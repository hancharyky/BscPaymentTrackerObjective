package bsc.example.objective.service;


import bsc.example.objective.enums.ConsoleCommands;
import bsc.example.objective.exception.InvalidUserInputException;
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

    public InputProcessorServiceImpl(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void processInput(InputStreamReader consoleReader) throws InvalidUserInputException {
        try(BufferedReader bufferedConsoleReader = new BufferedReader(consoleReader)){

            String line;

            while ((line = bufferedConsoleReader.readLine()) != null){
                boolean quit = ConsoleCommands.QUIT.toString().equalsIgnoreCase(line);
                if(quit){
                    return;
                }

                if (ValidationUtil.isInputPaymentValid(line)) {
                    log.info("Processing user input: " + line);
                    processInputLine(line);
                } else {
                    System.out.println("Input is invalid, please enter a payment in form \"USD 100.10\"");
                }
            }
        } catch (IOException e) {
            log.error("Input stream " + consoleReader + " is invalid", e);
            throw new InvalidUserInputException("Input stream " + consoleReader + " is invalid", e);

        }

    }

    @Override
    public void processInput(FileReader fileReader) throws InvalidUserInputException {
        try(BufferedReader bufferedFileReader = new BufferedReader(fileReader)) {
            String line;

            while ((line = bufferedFileReader.readLine()) != null) {
                if (ValidationUtil.isInputPaymentValid(line)) {
                    processInputLine(line);
                } else {
                    throw new InvalidUserInputException("The file contains invalid data");
                }
            }
        } catch (IOException e) {
            throw new InvalidUserInputException(e);
        }

    }


    private void processInputLine(String line){
        String[] parsedPayment = line.split(" ");

        String currency = parsedPayment[0];
        BigDecimal amount = new BigDecimal(parsedPayment[1]);

        bankAccountService.addPayment(currency, amount);


    }


}
