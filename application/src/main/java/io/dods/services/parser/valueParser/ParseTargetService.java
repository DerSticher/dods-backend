package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Target;
import io.dods.services.properties.target.TargetService;
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
public class ParseTargetService {

    private static final Pattern PATTERN = Pattern.compile("Zielkategorie ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<]+)");

    @Autowired
    private TargetService targetService;

    public List<Target> parseTargets(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String group = matcher.group(1);

            return parse(group);
        }

        return new ArrayList<>();
    }

    private List<Target> parse(String zielkategorien) {
        List<Target> list = new ArrayList<>();
        String[] split = zielkategorien.split("(?:[,]| und | oder )");

        for (String name : split) {
            name = name.trim();
            if (name.length() > 0 && !name.equals("und")) {
                Target target = targetService.findByNameOrCreate(name);
                list.add(target);
            }
        }

        return list;
    }

}
