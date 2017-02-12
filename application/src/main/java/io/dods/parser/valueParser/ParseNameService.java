package io.dods.parser.valueParser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseNameService {

    private static final Pattern CLEAR_PATTERN_TRADITION = Pattern.compile("(?:Die )?(Tradition \\([^\\)]+\\))(?: als Sonderfertigkeit)?");

    public String parseName(Document document) {
        Elements elements = document.getElementsByTag("h1");

        if (elements.size() > 0) {
            String text = elements.get(0).text();
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
