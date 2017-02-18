package io.dods.services.parser.valueParser;

import io.dods.services.properties.ability.AbilityService;
import io.dods.model.properties.Ability;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParsePrimaryAttributesService {

    private static final Pattern PATTERN = Pattern.compile("Leiteigenschaft ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<]*)");

    @Autowired
    private AbilityService abilityService;

    public Ability parsePrimaryAttributes(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String primaryAttributes = matcher.group(1).trim();
            String name = Jsoup.parse(primaryAttributes).text();
            return abilityService.findByName(name);
        }
        return null;
    }

}
