package instruction;

import java.util.AbstractMap.SimpleEntry;

/**
 * Enum of instructions & related methods
 *
 * @author Harrison Roberts
 */
public enum Instruction {
    DEB((val, args) -> {
        if (val >= 1)
            return new SimpleEntry<>(args[1], --val);
        return new SimpleEntry<>(args[2], val);
    }),
    INC((val, args) -> {
        return new SimpleEntry<>(args[1], ++val);
    }),
    HALT((val, args) -> {
        return new SimpleEntry<>(-1, 0);
    });

    private final Execute executionFunction;

    /**
     * @param executionFunction function that defines the command behaviour
     */
    Instruction(Execute executionFunction) {
        this.executionFunction = executionFunction;
    }

    /**
     * Executes command using the given arguments
     *
     * @param regVal The value at the register contained in the first position of args[]
     * @return A Simple Entry where Key = index of next instruction, Value = new register value.
     */
    public SimpleEntry<Integer, Integer> execute(int regVal, int... args) {
        return executionFunction.execute(regVal, args);
    }
}