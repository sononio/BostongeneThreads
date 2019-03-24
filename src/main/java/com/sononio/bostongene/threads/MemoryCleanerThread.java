package com.sononio.bostongene.threads;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Singleton memory cleaner.
 */
public class MemoryCleanerThread extends Thread {
    private static MemoryCleanerThread instance;
    private static final List<Integer> memory = Memory.getMemory();

    /**
     * Implements singleton pattern.
     * @return An instance of MemoryCleanerThread.
     */
    static MemoryCleanerThread getInstance() {
        if (instance == null)
            instance = new MemoryCleanerThread();

        return instance;
    }

    /**
     * Implements singleton pattern. Private constructor.
     */
    private MemoryCleanerThread(){
        this.setName("MemoryCleanerThread");
    }

    /**
     * Runs cleaner.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            removeMinimum();
        }
    }

    /**
     * Removes minimum from memory.
     */
    private void removeMinimum() {
        synchronized (memory) { //Using synchronized because of multi-thread architecture
            Optional<Integer> min = memory.stream().min(Comparator.comparing(x -> x));

            if (!min.isPresent())
                return;
            memory.remove(min.get());
            System.out.println(String.format("\n[MEMORY CLEANER] Removed minimum (%s) from values", min.get()));
        }
    }
}
