package bsc.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Process user input and provide bank account with payment info
 *
 * @author Yahor
 */
public class InputProcessor {

    private BankAccount bankAccount;

    public InputProcessor(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    /**
     * Method validate user input, as app accepts only certain values.
     *
     * @param bufferedReader is predefined buffer with payment info
     * @param inputType denote the type of input, which determines processing steps
     * @throws IOException
     */
    public void processInput(BufferedReader bufferedReader, String inputType) throws IOException {

        String line;
        while((line = bufferedReader.readLine()) != null){
            if ("quit".equalsIgnoreCase(line)) {
                break;
            }

            try{
                if (!line.matches("[A-Z]{3}\\s-?\\d{0,15}.\\d{0,15}")) {
                    switch(inputType){
                        case("FILE"): throw new IllegalArgumentException("Payments input file contains invalid values!");
                        case("CONSOLE"): System.out.println("Payment should be in form \"ABC (-)123456.789\" or \"quit\".");
                    }
                } else {
                    processInputLine(line);
                    System.out.println(bankAccount.toString());
                }
            } catch (IllegalArgumentException ex){
                System.out.println("There is a problem with input file!\n" + ex.getMessage()); // logger is more suitable
                System.exit(-1);
            }
        }

    }

    /**
     * Method takes payment string, split it in currency and amount, to persist it to bank account
     *
     * @param line input from file or console which contains validated currency code and amount
     */
    public void processInputLine(String line){
        String[] splittedTransaction = line.split(" ");
        String inputCurrency = splittedTransaction[0];
        BigDecimal inputAmount = BigDecimal.valueOf(Double.parseDouble(splittedTransaction[1]));
        bankAccount.transaction(inputCurrency, inputAmount);
    }
}
