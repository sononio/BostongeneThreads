package com.sononio.bostongene.threads;

import java.util.LinkedList;
import java.util.List;

/**
 * Singleton memory.
 */
public class Memory {
    private static List<Integer> memory;

    /**
     * Implements singleton pattern.
     * @return An instance of memory.
     */
    static List<Integer> getMemory() {
        if (memory == null) {
            memory = new LinkedList<>();
        }

        return memory;
    }

    /**
     * Implements singleton pattern. Private constructor.
     */
    private Memory(){}
}
