package io.dods.parser.valueParser;

import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.model.attribute.misc.Wirkungsdauer;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseWirkungService {

    private static final Pattern PATTERN_WIRKUNGSDAUER = Pattern.compile("Wirkungsdauer ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^\\n<]+)");

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    public Wirkungsdauer parseWirkungsdauer(Document document) {
        Matcher matcher = PATTERN_WIRKUNGSDAUER.matcher(document.html());

        if (matcher.find()) {
            String wirkungsdauer = matcher.group(1).trim();

            return wirkungsdauerService.findByNameOrCreate(wirkungsdauer);
        }

        return null;
    }

}
