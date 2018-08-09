package bsc.example.objective.util;

import java.util.Currency;

/**
 * Validation utility contains method for user input validation.
 *
 * @author Yahor
 */
public class ValidationUtil {


    private static final String PAYMENT_REGEX = "[A-Z]{3}\\s-?\\d{0,15}\\.?\\d{0,15}"; // e.g. USD -100.15 is valid value

    /**
     * Method validates input contains uppercase currency code and amount of payment splitted by space.
     * Integer and fractional part of amount must be separated by decimal point.
     *
     * @param line is a string representation of a payment
     * @return true if input matches payment regex and currency code exists
     */
    public static boolean isInputPaymentValid(String line) {
        String inputCurrencyCode = line.split(" ")[0];
        String currenciesList = Currency.getAvailableCurrencies().toString();
        return line.matches(PAYMENT_REGEX) && currenciesList.contains(inputCurrencyCode);
    }
}
