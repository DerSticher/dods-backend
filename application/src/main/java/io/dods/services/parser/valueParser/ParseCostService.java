package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Cost;
import io.dods.services.properties.cost.CostService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseCostService {

    private static final Pattern PATTERN = Pattern.compile("(?:AsP|KaP)\\-Kosten ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<\\n]+)");

    @Autowired
    private CostService costService;

    public Cost parseCost(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            return costService.findByNameOrCreate(matcher.group(1));
        }

        return null;
    }

}
