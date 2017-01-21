package io.dods.parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseNameService {

    private static final Pattern PATTERN = Pattern.compile("([\\wäöüÄÖÜß ]+) I-[IVX]+");

    private static final Pattern CLEAR_PATTERN_TRADITION = Pattern.compile("(?:Die )?(Tradition \\([^\\)]+\\))(?: als Sonderfertigkeit)?");

    public String parseName(Document document) {
        Elements elements = document.getElementsByTag("h1");

        if (elements.size() > 0) {
            String text = elements.get(0).text();
            Matcher matcher = PATTERN.matcher(text);
            if (matcher.find()) {
                return clearText(matcher.group(1));
            }
            return clearText(text);
        }

        return "";
    }

    private String clearText(String name) {
        Matcher traditionMatcher = CLEAR_PATTERN_TRADITION.matcher(name);
        if (traditionMatcher.find()) {
            name = traditionMatcher.group(1).trim();
        }

        return name;
    }

}
