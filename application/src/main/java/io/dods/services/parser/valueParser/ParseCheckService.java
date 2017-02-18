package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Check;
import io.dods.services.properties.check.CheckService;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseCheckService {

    private static final Pattern PATTERN = Pattern.compile("Probe ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?(..)/(..)/(..)");

    private final Map<String, Integer> idMap = new HashMap<>();

    public ParseCheckService() {
        idMap.put("MU", 1);
        idMap.put("KL", 2);
        idMap.put("IN", 3);
        idMap.put("CH", 4);
        idMap.put("FF", 5);
        idMap.put("GE", 6);
        idMap.put("KO", 7);
        idMap.put("KK", 8);
    }

    @Autowired
    private CheckService checkService;

    public Check parseProbe(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            int id1 = idMap.get(matcher.group(1));
            int id2 = idMap.get(matcher.group(2));
            int id3 = idMap.get(matcher.group(3));

            return checkService.create(id1, id2, id3);
        }

        return null;
    }

}
