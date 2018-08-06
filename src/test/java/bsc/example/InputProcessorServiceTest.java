package bsc.example;

import bsc.example.objective.exception.InvalidInputException;
import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.service.BankAccountServiceImpl;
import bsc.example.objective.service.InputProcessorService;
import bsc.example.objective.service.InputProcessorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InputProcessorServiceTest {

    private InputProcessorService inputProcessorService;
    @Before
    public void init(){
        this.inputProcessorService = new InputProcessorServiceImpl(new BankAccountServiceImpl(new AccountRepo()));
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFound() throws FileNotFoundException, InvalidInputException {
        inputProcessorService.processInput(new FileReader("paymentss.txt"));
    }


    @Test(expected = InvalidInputException.class)
    public void testInvalidInput() throws InvalidInputException, IOException {
        List<String> lines = Arrays.asList("USD 1000", "czk 15000");
        Path file = Paths.get("test.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
        inputProcessorService.processInput(new FileReader("test.txt"));
    }


}
