package io.dods.services.parser.valueParser;

import io.dods.model.attribute.misc.Kostentabelle;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseKostentabelleService {

    private static final Pattern PATTERN = Pattern.compile("Steigerungsfaktor ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([A-E]+)");

    public Kostentabelle parseKostentabelle(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return Kostentabelle.findOrThrow(matcher.group(1));
        }
        return null;
    }

}
