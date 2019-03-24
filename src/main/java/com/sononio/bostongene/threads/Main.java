package com.sononio.bostongene.threads;

/**
 * Class with main function.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ReaderThread readerThread = ReaderThread.getInstance();
        MemoryCleanerThread memoryCleanerThread = MemoryCleanerThread.getInstance();
        readerThread.start();
        memoryCleanerThread.start();

        while (true) {
            Thread.sleep(1000);
        }
    }
}
