package bsc.example;

import bsc.example.objective.service.InputProcessorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Yahor
 */
public class ValidationTest {
    InputProcessorServiceImpl inputProcessorService;
    String lineValid;
    String lineInvalid1;
    String lineInvalid2;

    @Before
    public void init(){
        inputProcessorService = new InputProcessorServiceImpl(null);
        lineValid = "USD -100.15";
        lineInvalid1 = "ABC 100.15";
        lineInvalid2 = "USd 100,15";
    }

    @Test
    public void validationTest(){
        assertTrue(inputProcessorService.isValid(lineValid));
    }

    @Test
    public void nonExistingCurrency(){
        assertFalse(inputProcessorService.isValid(lineInvalid1));
    }

    @Test
    public void invalidCurrency(){
        assertFalse(inputProcessorService.isValid(lineInvalid2));
    }
}
