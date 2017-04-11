package io.dods.services.parser.references;

import io.dods.services.parser.model.ParsedValue;
import io.dods.services.parser.valueParser.ParseApValueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseValueLevelService {

    private static final Pattern PATTERN_LEVEL = Pattern.compile(" I-([IVX]+)");

    private static final Pattern PATTERN_NAME = Pattern.compile("(.+?) I-([IVX]+)");

    public List<ParsedValue> checkForLevels(ParsedValue parsedValue) {
        List<ParsedValue> list = new ArrayList<>();

        if (hasLevel(parsedValue)) {
            list.addAll(parseLevels(parsedValue));
        } else {
            parsedValue.setApValue(parsedValue.getParsedApValue().getAp(1));
        }

        parsedValue.setName(getOriginalName(parsedValue));

        return list;
    }

    private List<ParsedValue> parseLevels(ParsedValue parsedValue) {
        List<ParsedValue> parsedValues = new ArrayList<>();

        for (int level = 1; level <= getMaxLevel(parsedValue); level++) {
            parsedValues.add(parseLevel(parsedValue, level));
        }

        return parsedValues;
    }

    public void setRealName(ParsedValue parsedValue) {
        String originalName = getOriginalName(parsedValue);
        parsedValue.setName(originalName);
    }

    private String getOriginalName(ParsedValue parsedValue) {
        Matcher matcher = PATTERN_NAME.matcher(parsedValue.getName());
        if (matcher.find()) {
            return matcher.group(1);
        }
        return parsedValue.getName();
    }

    private ParsedValue parseLevel(ParsedValue parsedValue, int level) {
        ParsedValue copy = parsedValue.copy();
        copy.setPublications(null);

        Matcher matcher = PATTERN_NAME.matcher(parsedValue.getName());
        if (matcher.find()) {
            String romanLevel = convertToRoman(level);
            copy.setName(romanLevel);
        }

        ParseApValueService.ParsedApValue apValue = parsedValue.getParsedApValue();
        copy.setApValue(apValue.getAp(level));

        return copy;
    }

    private boolean hasLevel(ParsedValue parsedValue) {
        return getMaxLevel(parsedValue) != 0;
    }

    private int getMaxLevel(ParsedValue parsedValue) {
        Matcher matcher = PATTERN_LEVEL.matcher(parsedValue.getName());

        if (matcher.find()) {
            return decode(matcher.group(1).trim());
        }

        return 0;
    }

    static int decodeSingle(char letter) {
        switch (letter) {
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X':
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
            default:
                throw new IllegalArgumentException("character '" + letter + "' is not mapped to a value");
        }
    }

    static int decode(String roman) {
        int result = 0;
        String uRoman = roman.toUpperCase(); //case-insensitive
        for (int i = 0; i < uRoman.length() - 1; i++) {//loop over all but the last character
            if (decodeSingle(uRoman.charAt(i)) < decodeSingle(uRoman.charAt(i + 1))) {
                result -= decodeSingle(uRoman.charAt(i));
            } else {
                result += decodeSingle(uRoman.charAt(i));
            }
        }
        result += decodeSingle(uRoman.charAt(uRoman.length() - 1));
        return result;
    }

    private static String convertToRoman(int mInt) {
        String[] rnChars = { "M",  "CM", "D", "C",  "XC", "L",  "X", "IX", "V", "I" };
        int[] rnVals = {  1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
        String retVal = "";

        for (int i = 0; i < rnVals.length; i++) {
            int numberInPlace = mInt / rnVals[i];
            if (numberInPlace == 0) continue;
            retVal += numberInPlace == 4 && i > 0? rnChars[i] + rnChars[i - 1]:
                    new String(new char[numberInPlace]).replace("\0",rnChars[i]);
            mInt = mInt % rnVals[i];
        }
        return retVal;
    }

}
