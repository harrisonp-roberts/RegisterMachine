package main;

import instruction.Instruction;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 * Controls the execution of the parsed instructions
 *
 * @author Harrison Roberts
 */
public class Executor {
    private int instructionsExecuted = 0;

    /**
     * Handles the execution of parsed instructions and registers
     *
     * @param registers       initialized array of registers
     * @param instructionList parsed list instructions as a list of simple entries where the key is the instruction,
     *                        and the value is the instruction arguments as an array of ints
     */
    void runProgram(int[] registers, List<SimpleEntry<Instruction, int[]>> instructionList) {
        int nextInstructionIndex = 1;

        final var start = System.currentTimeMillis();
        while (nextInstructionIndex > 0) {
            final SimpleEntry<Instruction, int[]> currentInstructionEntry = instructionList.get((nextInstructionIndex - 1));
            final var currentInstruction = currentInstructionEntry.getKey();
            final var currentInstructionArgs = currentInstructionEntry.getValue();
            final var currentRegisterIndex = currentInstructionArgs[0];
            final var currentRegisterValue = registers[currentRegisterIndex];
            final var executionResult = currentInstruction.execute(currentRegisterValue, currentInstructionArgs);

            printState(currentInstruction.name(), currentRegisterIndex, currentRegisterValue);

            registers[currentRegisterIndex] = executionResult.getValue();
            nextInstructionIndex = executionResult.getKey();
            instructionsExecuted++;
        }
        final var end = System.currentTimeMillis();
        final var diff = end - start;
        printSummary(registers, diff);
    }

    private void printState(String instruction, int index, int value) {
        System.out.println("**************Program State**************");
        System.out.println("Current Instruction: " + instruction);
        System.out.println("Current Register Index: " + index + ", Value: " + value);
    }

    private void printSummary(int[] registers, long elapsed) {
        System.out.println("--------------Execution Summary--------------");
        System.out.println("Total Instructions Executed: " + instructionsExecuted);
        System.out.println("Time Elapsed During Execution (ms): " + elapsed);
        System.out.println("Nonzero Registers:");
        for (int i = 0; i < registers.length; i++) {
            if (registers[i] != 0) {
                System.out.println("\tRegister " + i + ": " + registers[i]);
            }
        }
    }
}
