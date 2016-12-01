package io.dods.parser;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseKapKostenService {

    private static final Pattern PATTERN = Pattern.compile("KaP-Kosten:</strong> (\\d+) KaP");

    public int parseKapKosten(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        return 0;
    }

}
