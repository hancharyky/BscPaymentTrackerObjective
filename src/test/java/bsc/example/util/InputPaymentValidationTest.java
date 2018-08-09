package bsc.example.util;

import bsc.example.objective.util.ValidationUtil;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void init(){
        validLine = "USD -100.15";
        nonExistingCurrency = "ABC 100.15";
        invalidCurrency = "USd 100.15";
        invalidSeparator = "USD 100,15";
    }

    @Test
    public void validatePayment_valid(){
        assertTrue(ValidationUtil.isInputPaymentValid(validLine));
    }

    @Test
    public void validatePayment_nonExistingCurrency(){
        assertFalse(ValidationUtil.isInputPaymentValid(nonExistingCurrency));
    }

    @Test
    public void validatePayment_invalidCurrency(){
        assertFalse(ValidationUtil.isInputPaymentValid(invalidCurrency));
    }

    @Test
    public void validatePayment_invalidSeparator(){
        assertFalse(ValidationUtil.isInputPaymentValid(invalidSeparator));
    }
}
