package io.dods.parser;

import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.liturgie.LiturgieService;
import io.dods.attributeService.ritual.RitualService;
import io.dods.attributeService.segen.SegenService;
import io.dods.attributeService.zauber.ZauberService;
import io.dods.attributeService.zaubertrick.ZaubertrickService;
import io.dods.attributeService.zeremonie.ZeremonieService;
import io.dods.model.attribute.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        return elements.stream()
                .filter(element -> element.hasAttr("class"))
                .filter(element -> element.attr("class").equals("ulSubMenu"))
                .filter(element -> element.hasAttr("href"))
                .map(element -> element.attr("href"))
                .map(link -> link.startsWith("http://www.ulisses-regelwiki.de/")
                        ? link : "http://www.ulisses-regelwiki.de/" + link)
                .collect(Collectors.toList());
    }

    private <T extends Attribut> List<T> parse(AbstractAttributService<T> service, Stream<T> stream) {
        return stream
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .map(service::save)
                .collect(Collectors.toList());
    }

    public List<Liturgie> parseLiturgie(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(liturgieService, urls.stream()
                .map(url -> parserService.parseLiturgie(url)));
    }

    public List<Ritual> parseRitual(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(ritualService, urls.stream()
                .map(url -> parserService.parseRitual(url)));
    }

    public List<Segen> parseSegen(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(segenService, urls.stream()
                .map(url -> parserService.parseSegen(url)));
    }

    public List<Zauber> parseZauber(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(zauberService, urls.stream()
                .map(url -> parserService.parseZauber(url)));
    }

    public List<Zaubertrick> parseZaubertrick(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(zaubertrickService, urls.stream()
                .map(url -> parserService.parseZaubertrick(url)));
    }

    public List<Zeremonie> parseZeremonie(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(zeremonieService, urls.stream()
                .map(url -> parserService.parseZeremonie(url)));
    }

}
