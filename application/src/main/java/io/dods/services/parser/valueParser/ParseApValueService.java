package io.dods.services.parser.valueParser;

import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseApValueService {
    private static final Pattern PATTERN = Pattern.compile("AP\\-Wert ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([\\–\\-]?\\d+)");
    private static final Pattern PATTERN_LEVEL = Pattern.compile("AP\\-Wert ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?Stufe ([I/]+): ([-\\d/]+)");

    public ParsedApValue parseApValue(Document document) {
        ParsedApValue parsedApValue = parseLevels(document);

        if (parsedApValue == null) {
            parsedApValue = parseFix(document);
        }

        if (parsedApValue == null) {
            parsedApValue = new ParsedApValue();
        }

        return parsedApValue;
    }

    @Nullable
    private ParsedApValue parseLevels(Document document) {
        ParsedApValue parsedApValue = new ParsedApValue();

        Matcher matcher = PATTERN_LEVEL.matcher(document.text());

        if (matcher.find()) {
            HashMap<Integer, Integer> leveledValues = new HashMap<>();
            String aps = matcher.group(2);

            String[] apSplit = aps.split("/");

            for (int i = 0; i < apSplit.length; i++) {
                String ap = apSplit[i];

                ap = ap.replace('–', '-');
                leveledValues.put(i + 1, Integer.parseInt(ap));
            }


            parsedApValue.leveledValues = leveledValues;
        } else {
            return null;
        }
        return parsedApValue;
    }

    @Nullable
    private ParsedApValue parseFix(Document document) {
        Matcher matcher = PATTERN.matcher(document.text());

        if (matcher.find()) {
            String value = matcher.group(1);

            // replace wrong character before parsing it
            value = value.replace('–', '-');

            ParsedApValue parsedApValue = new ParsedApValue();
            parsedApValue.singleValue = Integer.parseInt(value);

            return parsedApValue;
        }
        return null;
    }

    public static class ParsedApValue implements Serializable {
        private int singleValue;
        private HashMap<Integer, Integer> leveledValues = new HashMap<>();

        public int getAp(int level) {
            if (leveledValues.size() > 0 && level <= leveledValues.size()) {
                return leveledValues.get(level);
            }
            return singleValue * level;
        }
    }

}
