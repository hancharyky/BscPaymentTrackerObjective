package bsc.example.util;

import bsc.example.objective.util.ValidationUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yahor
 */
public class InputPaymentValidationTest {
    String validLine;
    String nonExistingCurrency;
    String invalidCurrency;
    String invalidSeparator;
    Pattern PAYMENT_REGEX = Pattern.compile("[A-Z]{3}\\s-?\\d{0,15}\\.?\\d{0,15}"); // e.g. USD -100.15 is valid value

    @Before
    public void init(){
        validLine = "USD -100.15";
        nonExistingCurrency = "ABC 100.15";
        invalidCurrency = "USd 100.15";
        invalidSeparator = "USD 100,15";
    }

    @Test
    public void validatePayment_valid(){
        assertTrue(ValidationUtil.isInputPaymentValid(PAYMENT_REGEX, validLine));
    }

    @Test
    public void validatePayment_nonExistingCurrency(){
        assertFalse(ValidationUtil.isInputPaymentValid(PAYMENT_REGEX, nonExistingCurrency));
    }

    @Test
    public void validatePayment_invalidCurrency(){
        assertFalse(ValidationUtil.isInputPaymentValid(PAYMENT_REGEX, invalidCurrency));
    }

    @Test
    public void validatePayment_invalidSeparator(){
        assertFalse(ValidationUtil.isInputPaymentValid(PAYMENT_REGEX, invalidSeparator));
    }
}
