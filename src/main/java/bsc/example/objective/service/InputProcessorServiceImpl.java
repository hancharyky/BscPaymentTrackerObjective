package bsc.example.objective.service;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Process user input and pass it to transact it into bank account.
 *
 * @author Yahor
 */
public class InputProcessorServiceImpl implements InputProcessorService {
    BankAccountServiceImpl bankAccountService;
    private final String EXIT_COMMAND = "quit";
    private final int NORMAL_EXIT = 0;

    public InputProcessorServiceImpl(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Override
    public void processInput(InputStreamReader consoleReader) throws IOException {
        BufferedReader bufferedConsoleReader = new BufferedReader(consoleReader);
        String line;

        while ((line = bufferedConsoleReader.readLine()) != null){
            if(EXIT_COMMAND.equalsIgnoreCase(line)){
                System.exit(NORMAL_EXIT);
            }

            if (isValid(line)) {
                processInputLine(line);
            } else {
                System.out.println("Input is invalid, please enter a payment in form \"USD 100.10\"");
            }
        }


    }

    @Override
    public void processInput(FileReader fileReader) throws IllegalArgumentException, IOException {
        BufferedReader bufferedFileReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedFileReader.readLine()) != null){
            if (isValid(line)) {
                processInputLine(line);
            } else{
                throw new IllegalArgumentException("Input file contains invalid data.");

            }
        }


    }

    @Override
    public boolean isValid(String line) {
            String inpudCurrencyCode = line.split(" ")[0];
            String currenciesList = Currency.getAvailableCurrencies().toString();
            return line.matches("[A-Z]{3}\\s-?\\d{0,15}\\.?\\d{0,15}") && currenciesList.contains(inpudCurrencyCode);
    }

    private void processInputLine(String line){
        String[] parsedPayment = line.split(" ");

        String currency = parsedPayment[0];
        BigDecimal amount = new BigDecimal(parsedPayment[1]);

        bankAccountService.addPayment(currency, amount);


    }


}
