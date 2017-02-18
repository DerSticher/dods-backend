package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Aspect;
import io.dods.services.properties.aspect.AspectService;
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
public class ParseAspectService {

    private static final Pattern PATTERN = Pattern.compile("Aspekt ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<]+)");

    @Autowired
    private AspectService aspectService;

    public List<Aspect> parseAspect(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String group = matcher.group(1);

            return parse(group);
        }

        return new ArrayList<>();
    }

    private List<Aspect> parse(String aspects) {
        List<Aspect> list = new ArrayList<>();
        String[] split = aspects.split("[, ]");

        for (String name : split) {
            name = name.trim();
            if (name.length() > 0 && !name.equals("und")) {
                Aspect zielkategorie = aspectService.findByNameOrCreate(name);
                list.add(zielkategorie);
            }
        }

        return list;
    }

}
