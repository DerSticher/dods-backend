package io.dods.parser;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseAspKostenService {

    private static final Pattern PATTERN = Pattern.compile("AsP-Kosten:</strong> (\\d+) AsP");

    public int parseAspKosten(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return 0;
    }

}
