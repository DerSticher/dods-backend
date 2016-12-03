package io.dods.parser;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseApWertService {

    private static final Pattern PATTERN = Pattern.compile("AP\\-Wert(?:<[^>]+>)?:[^\\d–-]+([–-]?\\d+)");

    public int parseApWert(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String value = matcher.group(1);

            // replace wrong character before parsing it
            value = value.replace('–', '-');
            return Integer.parseInt(value);
        }

        return 0;
    }

}