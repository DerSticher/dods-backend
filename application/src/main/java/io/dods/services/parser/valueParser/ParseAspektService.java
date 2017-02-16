package io.dods.services.parser.valueParser;

import io.dods.services.attribute.aspekte.AspektService;
import io.dods.model.attribute.misc.Aspekt;
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
public class ParseAspektService {

    private static final Pattern PATTERN = Pattern.compile("Aspekt ?(?:<[^>]*>\\:|\\: ?<[^>]*>|\\:) ?([^<]+)");

    @Autowired
    private AspektService aspektService;

    public List<Aspekt> parseAspekt(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String group = matcher.group(1);

            return parse(group);
        }

        return new ArrayList<>();
    }

    private List<Aspekt> parse(String zielkategorien) {
        List<Aspekt> list = new ArrayList<>();
        String[] split = zielkategorien.split("[, ]");

        for (String name : split) {
            name = name.trim();
            if (name.length() > 0 && !name.equals("und")) {
                Aspekt zielkategorie = aspektService.findByNameOrCreate(name);
                list.add(zielkategorie);
            }
        }

        return list;
    }

}
