package instruction;

import java.util.AbstractMap.SimpleEntry;

/**
 * Functional interface defining the execution behaviour of an instruction.
 *
 * @author Harrison Roberts
 */
public interface Execute {

    /**
     * Doesn't know anything about WHICH register it is on, only knows the value
     *
     * @param args First arg is value of current register, second arg is L1, third is L2
     * @return SimpleEntry<nextExecutionLine, registerValue>
     */
    SimpleEntry<Integer, Integer> execute(int regVal, int... args);
}
