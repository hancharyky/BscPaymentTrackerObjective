package bsc.example.objective.service;

import bsc.example.objective.exception.InvalidInputException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implementation of the interface process and validation of user input in form of payments which could be passed,
 * e.g. as file or console input.
 *
 * @author Yahor
 */
public interface InputProcessorService {
    /**
     * Method processes and persist user console input
     *
     * @param consoleReader is a console input to be processed
     */
    void processInput(InputStreamReader consoleReader) throws InvalidInputException;

    /**
     * Method processes and persist user file input
     *
     * @param fileReader are payments in a file form
     */
    void processInput(FileReader fileReader) throws InvalidInputException;


}

