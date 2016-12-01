package io.dods.parser;

import io.dods.attributeService.liturgie.LiturgieService;
import io.dods.attributeService.ritual.RitualService;
import io.dods.attributeService.segen.SegenService;
import io.dods.attributeService.zauber.ZauberService;
import io.dods.attributeService.zaubertrick.ZaubertrickService;
import io.dods.attributeService.zeremonie.ZeremonieService;
import io.dods.model.attribute.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseListService {

    @Autowired
    private ParserService parserService;

    @Autowired
    private LiturgieService liturgieService;

    @Autowired
    private RitualService ritualService;

    @Autowired
    private SegenService segenService;

    @Autowired
    private ZauberService zauberService;

    @Autowired
    private ZaubertrickService zaubertrickService;

    @Autowired
    private ZeremonieService zeremonieService;

    private List<String> searchLinks(String url) {
        try {
            return trySearchLinks(url);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private List<String> trySearchLinks(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        Elements elements = document.getElementsByAttributeValue("role", "menuitem");

        List<String> urls = new ArrayList<>();

        for (Element element : elements) {
            String href = element.attr("href");
            if (!href.startsWith("http://www.ulisses-regelwiki.de/")) {
                href = "http://www.ulisses-regelwiki.de/" + href;
            }
            urls.add(href);
        }

        return urls;
    }

    public List<Liturgie> parseLiturgie(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseLiturgie(url))
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .map(liturgie -> liturgieService.save(liturgie))
                .collect(Collectors.toList());
    }

    public List<Ritual> parseRitual(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseRitual(url))
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .map(ritual -> ritualService.save(ritual))
                .collect(Collectors.toList());
    }

    public List<Segen> parseSegen(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseSegen(url))
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .map(segen -> segenService.save(segen))
                .collect(Collectors.toList());
    }

    public List<Zauber> parseZauber(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseZauber(url))
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .map(zauber -> zauberService.save(zauber))
                .collect(Collectors.toList());
    }

    public List<Zaubertrick> parseZaubertrick(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseZaubertrick(url))
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .map(zauber -> zaubertrickService.save(zauber))
                .collect(Collectors.toList());
    }

    public List<Zeremonie> parseZeremonie(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseZeremonie(url))
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .map(segen -> zeremonieService.save(segen))
                .collect(Collectors.toList());
    }

}
