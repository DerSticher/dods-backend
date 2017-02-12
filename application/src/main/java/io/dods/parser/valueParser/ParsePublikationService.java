package io.dods.parser.valueParser;

import io.dods.model.publikation.Publikation;
import io.dods.publikationService.PublikationService;
import io.dods.publikationService.WerkService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParsePublikationService {

    private static final Pattern PATTERN_WERKE = Pattern.compile("(.+?)\\W+Seite\\W+(.+)");

    private static final Pattern PATTERN = Pattern.compile("\\<p\\>Publikation: ([^<]+)");

    @Autowired
    private PublikationService publikationService;

    @Autowired
    private WerkService werkService;


    public List<Publikation> parsePublikations(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String text = matcher.group(1);

            Document parse = Jsoup.parse(text);

            return createPublications(parse.body().text());
        }

        return Collections.emptyList();
    }

    private List<Publikation> createPublications(String text) {
        List<Publikation> publikations = new ArrayList<>();

        text = text.replaceAll("[\\u00A0 ]", " ");

        String[] split = text.split("[,;]");
        for (String element : split) {
            Publikation publikation = createPublikation(element);
            if (publikation != null) publikations.add(publikation);
        }

        return publikations;
    }

    private Publikation createPublikation(String text) {
        Matcher matcher = PATTERN_WERKE.matcher(text);

        if (matcher.find()) {
            String werkName = matcher.group(1).trim();
            int page = Integer.parseInt(matcher.group(2).trim());

            return publikationService.findByWerkAndPageOrCreate(werkName, page);
        }

        return null;
    }

}
