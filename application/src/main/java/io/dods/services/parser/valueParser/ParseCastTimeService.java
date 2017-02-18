package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.CastTime;
import io.dods.services.properties.castTime.CastTimeService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseCastTimeService {

    private static final Pattern PATTERN = Pattern.compile("(?:Liturgiedauer|Ritualdauer|Zauberdauer|Zeremoniedauer) ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<\\n]+)");

    @Autowired
    private CastTimeService castTimeService;

    public CastTime parseCastTime(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());
        if (matcher.find()) {
            return castTimeService.findByNameOrCreate(matcher.group(1).trim());
        }
        return null;
    }

}
