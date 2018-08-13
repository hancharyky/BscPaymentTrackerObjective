package bsc.example.objective.util;

import java.util.Currency;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validation utility contains method for user input validation.
 *
 * @author Yahor
 */
public class ValidationUtil {


    /**
     * Method validates input contains uppercase currency code and amount of payment splitted by space.
     * Integer and fractional part of amount must be separated by decimal point.
     *
     * @param line is a string representation of a payment
     * @return true if input matches payment regex and currency code exists
     */
    public static boolean isInputPaymentValid(Pattern pattern, String line) {
        Matcher paymentMatcher = pattern.matcher("");
        String inputCurrencyCode = line.split(" ")[0];
        String currenciesList = Currency.getAvailableCurrencies().toString();


        return paymentMatcher.reset(line).matches() && currenciesList.contains(inputCurrencyCode);
    }
}
