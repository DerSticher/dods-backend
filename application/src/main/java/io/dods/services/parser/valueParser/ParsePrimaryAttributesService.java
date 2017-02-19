package io.dods.services.parser.valueParser;

import io.dods.model.properties.Attribute;
import io.dods.services.properties.ability.AttributeService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParsePrimaryAttributesService {

    private static final Pattern PATTERN = Pattern.compile("Leiteigenschaft ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<]*)");

    @Autowired
    private AttributeService attributeService;

    public List<Attribute> parsePrimaryAttributes(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String primaryAttributes = matcher.group(1).trim();
            String names = Jsoup.parse(primaryAttributes).text();
            return parseToList((names));
        }
        return null;
    }

    private List<Attribute> parseToList(String primaryAttributes) {
        List<Attribute> attributeList = new ArrayList<>();

        String[] split = primaryAttributes.split("/");

        for (String attributeName : split) {
            attributeList.add(attributeService.findByName(attributeName));
        }

        return attributeList;
    }

}
