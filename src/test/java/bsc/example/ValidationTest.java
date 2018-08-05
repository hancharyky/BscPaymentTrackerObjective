package bsc.example;

import bsc.example.objective.service.InputProcessorServiceImpl;
import bsc.example.objective.util.ValidationUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yahor
 */
public class ValidationTest {
    String lineValid;
    String lineInvalid1;
    String lineInvalid2;

    @Before
    public void init(){

        lineValid = "USD -100.15";
        lineInvalid1 = "ABC 100.15";
        lineInvalid2 = "USd 100,15";
    }

    @Test
    public void validationTest(){
        assertTrue(ValidationUtil.isValid(lineValid));
    }

    @Test
    public void nonExistingCurrency(){
        assertFalse(ValidationUtil.isValid(lineInvalid1));
    }

    @Test
    public void invalidCurrency(){
        assertFalse(ValidationUtil.isValid(lineInvalid2));
    }
}
