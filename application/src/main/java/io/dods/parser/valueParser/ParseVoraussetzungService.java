package io.dods.parser.valueParser;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseVoraussetzungService {

    private static final Pattern PATTERN = Pattern.compile("Voraussetzung(?:en)? ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^\\n<]+)");

    public String parseVoraussetzung(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return null;
    }

}
