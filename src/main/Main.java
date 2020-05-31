package main;

import parse.Parser;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Running Class for Assignment 1
 */
public class Main {
    final static String FILE_NAME = "/home/hroberts/Desktop/testDiv.txt";

    public static void main(String[] args) {
        var input = new Scanner(System.in);

        System.out.println("Please Enter the (absolute) Filepath of the RML Code you Would Like to Execute");
        final var filePath = input.nextLine();

        final var parser = new Parser();
        final var executor = new Executor();
        final int[] registers = new int[100000];

        try {
            final var inputRegisters = parser.parseHead(filePath);
            final var instructionList = parser.parseInstructions(filePath);

            inputRegisters.forEach(elem -> registers[elem.getKey()] = elem.getValue());
            executor.runProgram(registers, instructionList);
        } catch (FileNotFoundException e) {
            System.err.println("Invalid Filepath");
            System.err.println("Program Terminating");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            System.err.println("Program Terminating");
        }
    }
}