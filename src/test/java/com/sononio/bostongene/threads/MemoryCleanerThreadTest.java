package com.sononio.bostongene.threads;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryCleanerThreadTest {
    private static MemoryCleanerThread memoryCleanerThread;

    @BeforeAll
    static void setUp() {
        memoryCleanerThread = MemoryCleanerThread.getInstance();
    }

    @AfterAll
    static void tearDown() {
        memoryCleanerThread.stop();
    }

    @Test
    void getInstanceShouldReturnSingletonMemoryCleanerThreadTest() {
        MemoryCleanerThread memoryCleanerThread1 = MemoryCleanerThread.getInstance();

        assertSame(memoryCleanerThread, memoryCleanerThread1, "Should be the same");
    }

    @Test
    void runShouldRemoveMinimumFromMemory() throws InterruptedException {
        List<Integer> memory = Memory.getMemory();

        memory.add(4);
        memory.add(3);
        memory.add(5);

        memoryCleanerThread.start();
        Thread.sleep(6000);

        assertEquals(2, memory.size(), "One element should be removed");
        assertTrue(memory.contains(4) && memory.contains(5), "Now memory should contains 4 and 5");

        memoryCleanerThread.stop();
    }
}