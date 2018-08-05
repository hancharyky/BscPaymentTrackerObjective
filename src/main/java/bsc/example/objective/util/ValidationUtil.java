package bsc.example.objective.util;

import java.util.Currency;

/**
 * @author Yahor
 */
public class ValidationUtil {

    private static final String PAYMENT_REGEX = "[A-Z]{3}\\s-?\\d{0,15}\\.?\\d{0,15}";

    /**
     * Accepts separate line of input and checks if it valid input for processing.
     *
     * @param line is a string representation of a payment
     * @return
     */
    public static boolean isValid(String line) {
        String inputCurrencyCode = line.split(" ")[0];
        String currenciesList = Currency.getAvailableCurrencies().toString();
        return line.matches(PAYMENT_REGEX) && currenciesList.contains(inputCurrencyCode);
    }
}
