package io.dods.parser;

import io.dods.attributeService.dauer.DauerService;
import io.dods.model.attribute.misc.Dauer;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseDauerService {

    private static final Pattern PATTERN = Pattern.compile("(?:Liturgiedauer|Ritualdauer|Zauberdauer|Zeremoniedauer):<[^>]+>([^<]+)");

    @Autowired
    private DauerService dauerService;

    public Dauer parseDauer(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());
        if (matcher.find()) {
            return dauerService.findByNameOrCreate(matcher.group(1).trim());
        }
        return null;
    }

}
