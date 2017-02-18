package io.dods.services.parser.valueParser;

import io.dods.services.properties.duration.DurationService;
import io.dods.model.properties.misc.Duration;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseDurationService {

    private static final Pattern PATTERN_WIRKUNGSDAUER = Pattern.compile("Wirkungsdauer ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^\\n<]+)");

    @Autowired
    private DurationService durationService;

    public Duration parseDuration(Document document) {
        Matcher matcher = PATTERN_WIRKUNGSDAUER.matcher(document.html());

        if (matcher.find()) {
            String wirkungsdauer = matcher.group(1).trim();

            return durationService.findByNameOrCreate(wirkungsdauer);
        }

        return null;
    }

}
