package io.dods.parser;

import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.model.attribute.misc.Reichweite;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseReichweiteService {

    private static final Pattern PATTERN = Pattern.compile("Reichweite ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<\\n]+)");

    @Autowired
    private ReichweiteService reichweiteService;

    public Reichweite parseReichweite(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String reichweite = matcher.group(1);

            return reichweiteService.findByNameOrCreate(reichweite);
        }

        return null;
    }

}
