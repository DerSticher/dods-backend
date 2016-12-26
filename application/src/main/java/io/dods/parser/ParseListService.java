package io.dods.parser;

import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.attribute.AttributeService;
import io.dods.attributeService.liturgie.LiturgieService;
import io.dods.attributeService.ritual.RitualService;
import io.dods.attributeService.segen.SegenService;
import io.dods.attributeService.sonderfertigkeit.SonderfertigkeitService;
import io.dods.attributeService.vorteil.VorteilService;
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
    private AttributeService attributeService;

    @Autowired
    private LiturgieService liturgieService;

    @Autowired
    private RitualService ritualService;

    @Autowired
    private SonderfertigkeitService sonderfertigkeitService;

    @Autowired
    private SegenService segenService;

    @Autowired
    private VorteilService vorteilService;

    @Autowired
    private ZauberService zauberService;

    @Autowired
    private ZaubertrickService zaubertrickService;

    @Autowired
    private ZeremonieService zeremonieService;

    @Autowired
    private VoraussetzungService voraussetzungService;

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
                .filter(value -> service.findByName(value.getName()) == null)
                .map(value -> checkForUpdateOrPersist(service, value))
                .collect(Collectors.toList());
    }

    private <T extends Attribut> T checkForUpdateOrPersist(AbstractAttributService<T> service, T attribut) {
        T byName = service.findByName(attribut.getName());

        if (byName != null) {
            byName.setWikiUrl(attribut.getWikiUrl());
            service.save(byName);

            return byName;
        } else {
            service.save(attribut);
        }

        return attribut;
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

    public List<Sonderfertigkeit> parseSonderfertigkeit(Sonderfertigkeit.Gruppe gruppe, String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(sonderfertigkeitService, urls.stream()
                .map(url -> parserService.parseSonderfertigkeit(url, gruppe))
                .flatMap(List::stream));
    }

    public List<Segen> parseSegen(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(segenService, urls.stream()
                .map(url -> parserService.parseSegen(url)));
    }

    public List<Vorteil> parseVorteil(String listUrl) {
        List<String> urls = searchLinks(listUrl);

        return parse(vorteilService, urls.stream()
                .map(url -> parserService.parseVorteil(url))
                .flatMap(List::stream));
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

    public void parseVorraussetzungen() {
        Iterable<Attribut> attributs = attributeService.find(null, null, false);

        for (Attribut attribut : attributs) {
            voraussetzungService.parseNewVoraussetzung(attribut);
        }
    }

}
