package io.dods.parser;

import io.dods.attributeService.nutzkosten.NutzkostenService;
import io.dods.model.attribute.misc.Nutzkosten;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseNutzkostenService {

    private static final Pattern PATTERN = Pattern.compile("(?:AsP|KaP)\\-Kosten ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<\\n]+)");

    @Autowired
    private NutzkostenService nutzkostenService;

    public Nutzkosten parseNutzkosten(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return nutzkostenService.findByNameOrCreate(matcher.group(1));
        }

        return null;
    }

}
