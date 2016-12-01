package io.dods.parser;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
class ParseNameService {

    public String parseName(Document document) {
        Elements elements = document.getElementsByTag("h1");

        if (elements.size() > 0) {
            return elements.get(0).text();
        }

        return "";
    }

}
