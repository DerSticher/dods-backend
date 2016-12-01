package io.dods.parser;

import io.dods.model.attribute.misc.Kostentabelle;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseKostentabelleService {

    private static final Pattern PATTERN = Pattern.compile("Steigerungsfaktor: ?(?:<[^>]+>)? ?([^<]+)");

    public Kostentabelle parseKostentabelle(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return Kostentabelle.findOrThrow(matcher.group(1));
        }
        return null;
    }

}
