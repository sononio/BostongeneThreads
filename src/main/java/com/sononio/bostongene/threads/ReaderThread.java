package com.sononio.bostongene.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Singleton stdin reader.
 */
public class ReaderThread extends Thread {
    private static ReaderThread instance;
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static final List<Integer> memory = Memory.getMemory();

    /**
     * Implements singleton pattern. Private constructor.
     */
    private ReaderThread() {
        this.setName("ReaderThread");
    }

    /**
     * Implements singleton pattern.
     * @return An instance of ReaderThread.
     */
    static ReaderThread getInstance() {
        if (instance == null)
            instance = new ReaderThread();

        return instance;
    }

    /**
     * Runs reader.
     */
    @Override
    public void run() {
        while (true) {
            parseNextInteger();
        }
    }

    private void parseNextInteger() {
        System.out.print("[READER] Please, enter a number: ");

        String s = null;
        try {
            s = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (s != null && !s.equals("")) {
            try {
                synchronized (memory) { //Using synchronized because of multi-thread architecture
                    memory.add(NumberParser.parseNumber(s));
                }

            } catch (IllegalArgumentException e) {
                System.out.println("[READER] Error: " + e.getMessage());
            }
        }
    }
}
