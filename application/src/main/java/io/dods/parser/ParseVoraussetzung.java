package io.dods.parser;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseVoraussetzung {

    private static final Pattern PATTERN = Pattern.compile("Voraussetzung(?:en)?: ?(?:<[^>]+>)? ?([^<]+)");

    public String parseVoraussetzung(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return null;
    }

}
