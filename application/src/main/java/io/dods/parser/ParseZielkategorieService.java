package io.dods.parser;

import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.misc.Zielkategorie;
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
class ParseZielkategorieService {

    private static final Pattern PATTERN = Pattern.compile("Zielkategorie:<[^>]+>([^<]+)");

    @Autowired
    private ZielkategorieService zielkategorieService;

    public List<Zielkategorie> parseZielkategorie(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String group = matcher.group(1);

            return parse(group);
        }

        return new ArrayList<>();
    }

    private List<Zielkategorie> parse(String zielkategorien) {
        List<Zielkategorie> list = new ArrayList<>();
        String[] split = zielkategorien.split("(?:[,]| und | oder )");

        for (String name : split) {
            name = name.trim();
            if (name.length() > 0 && !name.equals("und")) {
                Zielkategorie zielkategorie = zielkategorieService.findByNameOrCreate(name);
                list.add(zielkategorie);
            }
        }

        return list;
    }

}
