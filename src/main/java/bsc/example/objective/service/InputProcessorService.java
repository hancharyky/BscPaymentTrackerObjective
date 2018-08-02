package bsc.example.objective.service;

import java.io.BufferedReader;
import java.io.IOException;

public interface InputProcessorService {
    void processInput(BufferedReader bufferedConsoleReader, String console) throws IOException;
    boolean isValid(String line);
}

