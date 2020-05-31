package parse;

import instruction.Instruction;

import java.io.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    final ArrayList<String> VALID_INSTRUCTIONS = new ArrayList<>(Arrays.asList("DEB", "INC", "HALT"));

    public ArrayList<SimpleEntry<Instruction, int[]>> parseInstructions(String filename) throws Exception {
        final var instructions = new ArrayList<SimpleEntry<Instruction, int[]>>();
        final var file = new File(filename);

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        try (final var reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                final Instruction instruction;
                final var scanner = new Scanner(line).useDelimiter(" ");
                final var instructionType = scanner.next();

                try {
                    if (VALID_INSTRUCTIONS.contains(instructionType)) {
                        instruction = Enum.valueOf(Instruction.class, instructionType);

                        var args = new ArrayList<Integer>();
                        scanner.forEachRemaining(elem -> args.add(Integer.parseInt(elem)));
                        int[] primitiveArgs = convertArray(args);

                        instructions.add(new SimpleEntry<>(instruction, primitiveArgs));

                    }
                } catch (NumberFormatException ex) {
                    throw new Exception("Invalid Argument in line: \" " + line + "\"");
                }
            }
        }
        return instructions;
    }

    public ArrayList<SimpleEntry<Integer, Integer>> parseHead(String fileName) throws IOException {
        final var initRegisters = new ArrayList<SimpleEntry<Integer, Integer>>();
        final var file = new File(fileName);

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        try (final var reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            final var sc = new Scanner(line);
            sc.useDelimiter(" ");

            final String programName = sc.next();

            System.out.println("Running Program " + programName + "...");

            while (sc.hasNext()) {
                int regIndex = Integer.parseInt(sc.next().replaceAll("[\\D]", ""));
                int regVal = Integer.parseInt(sc.next());
                initRegisters.add(new SimpleEntry<>(regIndex, regVal));
            }
        }
        return initRegisters;
    }

    private int[] convertArray(ArrayList<Integer> toConvert) {
        int[] converted = new int[toConvert.size()];
        for (int i = 0; i < toConvert.size(); i++) {
            converted[i] = toConvert.get(i);
        }
        return converted;
    }
}

