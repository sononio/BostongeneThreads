package com.sononio.bostongene.threads;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberParserTest {

    @Test
    void parseNumberShouldThrowIllegalArgumentExceptionForNotNumber() {
        assertThrows(IllegalArgumentException.class, () -> NumberParser.parseNumber("Random Thing"));
    }

    @Test
    void parseNumberShouldThrowIllegalArgumentExceptionForNumberNotInAllowedRange() {
        assertThrows(IllegalArgumentException.class, () -> NumberParser.parseNumber("ten thousands"));
        assertThrows(IllegalArgumentException.class, () -> NumberParser.parseNumber("zero"));
        assertThrows(IllegalArgumentException.class, () -> NumberParser.parseNumber("minus one"));
    }

    @Test
    void parseNumberShouldParseTextWithCorrectNumber() {
        Map<String, Integer> testVals = new HashMap<>();
        testVals.put("one", 1);
        testVals.put("eleven", 11);
        testVals.put("twenty", 20);
        testVals.put("twenty two", 22);
        testVals.put("three hundred", 300);
        testVals.put("three hundred twenty two", 322);
        testVals.put("three hundred twelve", 312);
        testVals.put("one thousand", 1000);
        testVals.put("one thousand one", 1001);
        testVals.put("one thousand eleven", 1011);
        testVals.put("one thousand twenty", 1020);
        testVals.put("one thousand twenty two", 1022);
        testVals.put("one thousand three hundred", 1300);
        testVals.put("one thousand three hundred twenty two", 1322);
        testVals.put("one thousand three hundred twelve", 1312);

        for (Map.Entry<String, Integer> testVal : testVals.entrySet()) {
            assertEquals(
                    testVal.getValue(),
                    NumberParser.parseNumber(testVal.getKey()),
                    String.format("Should parse \"%s\" as %s", testVal.getKey(), testVal.getValue()));
        }
    }

    @Test
    void parseNumberShouldParseEveryCase() {
        Map<String, Integer> testVals = new HashMap<>();
        testVals.put("Three", 3);
        testVals.put("three", 3);
        testVals.put("THREE", 3);
        testVals.put("ThReE", 3);

        for (Map.Entry<String, Integer> testVal : testVals.entrySet()) {
            assertEquals(
                    testVal.getValue(),
                    NumberParser.parseNumber(testVal.getKey()),
                    String.format("Should parse \"%s\" as %s", testVal.getKey(), testVal.getValue()));
        }
    }
}