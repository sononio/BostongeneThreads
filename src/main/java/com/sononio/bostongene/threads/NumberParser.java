package com.sononio.bostongene.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utils for parsing text strings as integers.
 */
public class NumberParser {

    //Regular expressions for parse string
    private static final String UNITS = "one|two|three|four|five|six|seven|eight|nine";
    private static final String TENS = "twenty|thirty|fourty|fifty|sixty|seventy|eighty|ninety";
    private static final String SPECIFIC_TENS = "ten|eleven|twelve|thirteen|fourteen|fifteen|sixteen|seventeen|eighteen|nineteen";
    private static final Pattern numberPattern;

    //Containers for converting word to integer. For example "twenty" -> 20
    private static final Map<String, Integer> unitsValues;
    private static final Map<String, Integer> tensValues;
    private static final Map<String, Integer> specificsValues;

    static {
        numberPattern = Pattern.compile(
                "^((?<thousands>"+UNITS+") thousands? ?)?" +
                "((?<hundreds>"+UNITS+") hundreds? ?)?" +
                "(((?<tens>"+TENS+")? ?(?<units>"+UNITS+")?)|(?<specific>"+SPECIFIC_TENS+"))?$"
        );

        unitsValues = new HashMap<>();
        tensValues = new HashMap<>();
        specificsValues = new HashMap<>();

        unitsValues.put(null, 0);
        tensValues.put(null, 0);
        specificsValues.put(null, 0);

        //Filling map with words ["one".."nine"]
        int value = 1;
        for (String unit : UNITS.split("\\|")) {
            unitsValues.put(unit, value);
            value += 1;
        }

        //Filling map with words ["twenty".."ninety"]
        value = 20;
        for (String unit : TENS.split("\\|")) {
            tensValues.put(unit, value);
            value += 10;
        }

        //Filling map with words ["ten".."nineteen"]
        value = 10;
        for (String unit : SPECIFIC_TENS.split("\\|")) {
            specificsValues.put(unit, value);
            value += 1;
        }
    }


    /**
     * Parses integer number from string<br>
     * For example for string "One thousand two hundred eleven" method returns 1211<br>
     * Allowable values: [1..9999]
     * @param text Text string to parse integer number
     * @return Parsed integer number
     * @throws IllegalArgumentException Throws exception if text can't be parsed or value is not in allowable range
     */
    public static Integer parseNumber(String text) throws IllegalArgumentException {
        Matcher matcher =  numberPattern.matcher(text.toLowerCase());

        //Trying to parse text via regular expression
        if (!matcher.find())
            throw new IllegalArgumentException(String.format("Can't parse number \"%s\"", text));

        //Building an integer from text
        Integer res = 0;

        //[one] thousand two hundred thirty four
        res += 1000 * unitsValues.get(matcher.group("thousands"));

        //one thousand [two] hundred thirty four
        res += 100 * unitsValues.get(matcher.group("hundreds"));

        //one thousand two hundred [thirty] four
        res += tensValues.get(matcher.group("tens"));

        //one thousand two hundred thirty [four]
        res += unitsValues.get(matcher.group("units"));

        //one thousand two hundred [thirteen]
        res += specificsValues.get(matcher.group("specific"));

        if (res == 0)
            throw new IllegalArgumentException("Number value must be between 1..9999, not 0");

        return res;
    }
}
