package com.sononio.bostongene.threads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MemoryTest {
    private List<Integer> memory;

    @BeforeEach
    void setUp() {
        memory = Memory.getMemory();
    }

    @Test
    void getMemoryShouldReturnInitializedList() {
        assertNotNull(memory, "Should be initialized");
    }

    @Test
    void getMemoryShouldReturnSingletonList(){
        List<Integer> memory2 = Memory.getMemory();

        assertEquals(memory, memory2, "Should be the same");
    }
}