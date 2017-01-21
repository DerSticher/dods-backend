package io.dods.parser;

import io.dods.attributeService.eigenschaft.EigenschaftService;
import io.dods.model.attribute.Eigenschaft;
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
class ParseLeiteigenschaftService {

    private static final Pattern PATTERN_WIRKUNG = Pattern.compile("Leiteigenschaft ?: ?(?:<[^>]+>)?(.+?)(?:</?p>|</?strong>)");

    @Autowired
    private EigenschaftService eigenschaftService;

    public Eigenschaft parseLeiteigenschaft(Document document) {
        Matcher matcher = PATTERN_WIRKUNG.matcher(document.html());

        if (matcher.find()) {
            String leiteigenschaft = matcher.group(1).trim();
            String name = Jsoup.parse(leiteigenschaft).text();
            return eigenschaftService.findByName(name);
        }
        return null;
    }

}
