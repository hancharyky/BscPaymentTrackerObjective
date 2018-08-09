package bsc.example.service;

import bsc.example.objective.exception.InvalidUserInputException;
import bsc.example.objective.repo.AccountRepo;
import bsc.example.objective.service.BankAccountServiceImpl;
import bsc.example.objective.service.InputProcessorService;
import bsc.example.objective.service.InputProcessorServiceImpl;
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

/**
 * @author Yahor
 */
public class InputProcessorServiceTest {

    private InputProcessorService inputProcessorService;

    @Before
    public void init(){
        this.inputProcessorService = new InputProcessorServiceImpl(new BankAccountServiceImpl(new AccountRepo()));
    }

    @Test(expected = FileNotFoundException.class)
    public void inputProcessor_fileNotFound() throws FileNotFoundException, InvalidUserInputException {
        inputProcessorService.processInput(new FileReader("paymentss.txt"));
    }

    @Test(expected = InvalidUserInputException.class)
    public void inputProcessor_invalidInput() throws InvalidUserInputException, IOException {
        List<String> lines = Arrays.asList("USD 1000", "czk 15000");
        Path file = Paths.get("test.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
        inputProcessorService.processInput(new FileReader("test.txt"));
    }


}
