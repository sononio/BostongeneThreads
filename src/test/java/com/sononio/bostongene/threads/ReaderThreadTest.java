package com.sononio.bostongene.threads;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class ReaderThreadTest {
    private static ReaderThread readerThread;

    @BeforeAll
    static void setUp() {
        readerThread = ReaderThread.getInstance();
    }

    @AfterAll
    static void tearDown() {
        readerThread.stop();
    }

    @Test
    void getInstanceShouldReturnSingletonReaderThreadTest() {
        ReaderThread readerThread1 = ReaderThread.getInstance();
        assertSame(readerThread, readerThread1, "Should be the same");
    }
}