package bsc.example.objective.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Currency;

/**
 * Process user input and transact it into bank account.
 *
 * @author Yahor
 */
public class InputProcessorServiceImpl implements InputProcessorService {
    BankAccountServiceImpl bankAccountService;

    public InputProcessorServiceImpl(BankAccountServiceImpl bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    public void processInput(BufferedReader bufferedReader, String inputType) throws IOException {
        String line;
        while((line = bufferedReader.readLine()) != null) {

            if("quit".equalsIgnoreCase(line)){
                System.exit(0);
            }

            if (isValid(line)) {
                processInputLine(line, inputType);
            } else{
                switch (inputType){
                    case("FILE"): System.out.println("------ File input is invalid. Please check the file. ------"); break; //TODO: if file is invalid, revert changes
                    case("CONSOLE"): System.out.println("Console input is invalid"); break;
                }

            }
        }
    }

    private void processInputLine(String line, String inputType) {
        String[] parsedPayment = line.split(" ");

        String currency = parsedPayment[0];
        BigDecimal amount = new BigDecimal(parsedPayment[1]);

        if("RATES".equalsIgnoreCase(inputType)){
            //bankAccountService.convert(currency, amount);
        } else {
            bankAccountService.transaction(currency, amount);
        }

    }

    /**
     * Validates if input line is in valid
     * @param line
     * @return
     */
    @Override
    public boolean isValid(String line) {
        String inpudCurrencyCode = line.split(" ")[0];
        String currenciesList = Currency.getAvailableCurrencies().toString();
        return line.matches("[A-Z]{3}\\s-?\\d{0,15}.\\d{0,15}") && currenciesList.contains(inpudCurrencyCode);
    }
}
