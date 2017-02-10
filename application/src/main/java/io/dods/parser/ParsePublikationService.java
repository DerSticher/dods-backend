package io.dods.parser;

import io.dods.model.publikation.Publikation;
import io.dods.model.publikation.Werk;
import io.dods.publikationService.PublikationService;
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
class ParsePublikationService {

    private static final Pattern PATTERN_WERKE = Pattern.compile("(.+?)\\W+Seite\\W+(.+)");

    private static final Pattern PATTERN = Pattern.compile("\\<p\\>Publikation: ([^<]+)");

    @Autowired
    private PublikationService publikationService;

    public List<Publikation> parsePublikations(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String text = matcher.group(1);

            Document parse = Jsoup.parse(text);

            return createPublications(parse.body().text());
        }

        return null;
    }

    private List<Publikation> createPublications(String text) {
        List<Publikation> publikations = new ArrayList<>();

        String[] split = text.split("[,;]");
        for (String element : split) {
            Publikation publikation = createPublikation(element);
            if (publikation != null) publikations.add(publikation);
        }

        return publikations;
    }

    private Publikation createPublikation(String text) {
        Werk werk = null;
        String details = null;

        Matcher matcher = PATTERN_WERKE.matcher(text);
        if (matcher.find()) {
            werk = parseWerk(matcher.group(1).trim());
            details = matcher.group(2).trim();
        }

        if (werk != null) {
            Publikation publikation = new Publikation();
            publikation.setWerk(werk);
            publikation.setDetails(details);
            return publikation;
        }

        return null;
    }

    private Werk parseWerk(String text) {
        return publikationService.findByNameOrCreate(text);
    }

}
