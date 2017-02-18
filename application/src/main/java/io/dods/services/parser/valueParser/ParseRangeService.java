package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Range;
import io.dods.services.properties.range.RangeService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseRangeService {

    private static final Pattern PATTERN = Pattern.compile("Reichweite ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<\\n]+)");

    @Autowired
    private RangeService rangeService;

    public Range parseRange(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String reichweite = matcher.group(1);

            return rangeService.findByNameOrCreate(reichweite);
        }

        return null;
    }

}
