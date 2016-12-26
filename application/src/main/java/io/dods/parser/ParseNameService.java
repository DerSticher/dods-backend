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

    private static final Pattern PATTERN = Pattern.compile("([\\wäöüÄÖÜß]+) I-[IVX]+");

    public String parseName(Document document) {
        Elements elements = document.getElementsByTag("h1");

        if (elements.size() > 0) {
            String text = elements.get(0).text();
            Matcher matcher = PATTERN.matcher(text);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return text;
        }

        return "";
    }

}
