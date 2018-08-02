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

    @Before
    public void init(){
        inputProcessorService = new InputProcessorServiceImpl(null);
    }

    @Test
    public void validationTest(){
        String lineValid = "USD -100.15";
        String lineInvalid1 = "ABC 100,15";
        String lineInvalid2 = "USd 100.15";

        assertTrue(inputProcessorService.isValid(lineValid));
        assertFalse(inputProcessorService.isValid(lineInvalid1));
        assertFalse(inputProcessorService.isValid(lineInvalid2));

    }
}
