package io.dods.services.parser.valueParser;

import io.dods.model.publication.Publication;
import io.dods.services.publication.PublicationService;
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
public class ParsePublicationService {

    private static final Pattern PATTERN_WERKE = Pattern.compile("(.+?)\\W+Seite\\W+(.+)");

    private static final Pattern PATTERN = Pattern.compile("\\<p\\>Publikation: ([^<]+)");

    @Autowired
    private PublicationService publicationService;

    public List<Publication> parsePublications(Document document) {
        Matcher matcher = PATTERN.matcher(document.html());

        if (matcher.find()) {
            String text = matcher.group(1);

            Document parse = Jsoup.parse(text);

            return createPublications(parse.body().text());
        }

        return Collections.emptyList();
    }

    private List<Publication> createPublications(String text) {
        List<Publication> publications = new ArrayList<>();

        text = text.replaceAll("[\\u00A0 ]", " ");

        String[] split = text.split("[,;]");
        for (String element : split) {
            Publication publication = createPublication(element);
            if (publication != null) publications.add(publication);
        }

        return publications;
    }

    private Publication createPublication(String text) {
        Matcher matcher = PATTERN_WERKE.matcher(text);

        if (matcher.find()) {
            String bookName = matcher.group(1).trim();
            int page = Integer.parseInt(matcher.group(2).trim());

            return publicationService.findByBookAndPageOrCreate(bookName, page);
        }

        return null;
    }

}
