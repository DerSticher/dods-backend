package io.dods.parser;

import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.model.attribute.misc.Wirkungsdauer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseWirkungService {

    private static final Pattern PATTERN_WIRKUNG = Pattern.compile("Wirkung ?:<[^>]+>([^<]+)");

    private static final Pattern PATTERN_WIRKUNGSDAUER = Pattern.compile("Wirkungsdauer:<[^>]+>([^<]+)");

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    public String parseWirkung(Document document) {
        Matcher matcher = PATTERN_WIRKUNG.matcher(document.html());

        if (matcher.find()) {
            return matcher.group(1).trim();
        } else {
            return parseDescription(document);
        }
    }

    private String parseDescription(Document document) {
        Elements h1 = document.getElementsByTag("h1");
        if (h1.size() > 0) {
            Element element = h1.get(0).nextElementSibling();
            List<TextNode> textNodes = element.textNodes();
            TextNode textNode = textNodes.get(0);
            return textNode.text();
        }

        return "";
    }

    public Wirkungsdauer parseWirkungsdauer(Document document) {
        Matcher matcher = PATTERN_WIRKUNGSDAUER.matcher(document.html());

        if (matcher.find()) {
            String wirkungsdauer = matcher.group(1).trim();

            return wirkungsdauerService.findByNameOrCreate(wirkungsdauer);
        }

        return null;
    }

}
