package io.dods.parser;

import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.attribute.AttributeService;
import io.dods.attributeService.kampftechnik.KampftechnikService;
import io.dods.attributeService.liturgie.LiturgieService;
import io.dods.attributeService.ritual.RitualService;
import io.dods.attributeService.segen.SegenService;
import io.dods.attributeService.sonderfertigkeit.SonderfertigkeitService;
import io.dods.attributeService.vorteil.VorteilService;
import io.dods.attributeService.zauber.ZauberService;
import io.dods.attributeService.zaubertrick.ZaubertrickService;
import io.dods.attributeService.zeremonie.ZeremonieService;
import io.dods.model.attribute.*;
import io.dods.parser.model.ParsedData;
import io.dods.parser.valueParser.VoraussetzungService;
import io.dods.services.misc.LoggerService;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
public class ParseListService {

    private final ParserService parserService;

    private final AttributeService attributeService;

    private final KampftechnikService kampftechnikService;

    private final LiturgieService liturgieService;

    private final RitualService ritualService;

    private final SonderfertigkeitService sonderfertigkeitService;

    private final SegenService segenService;

    private final VorteilService vorteilService;

    private final ZauberService zauberService;

    private final ZaubertrickService zaubertrickService;

    private final ZeremonieService zeremonieService;

    private final VoraussetzungService voraussetzungService;

    private final LoggerService loggerService;

    @Autowired
    public ParseListService(ParserService parserService,
                            KampftechnikService kampftechnikService,
                            AttributeService attributeService,
                            LiturgieService liturgieService,
                            RitualService ritualService,
                            SonderfertigkeitService sonderfertigkeitService,
                            SegenService segenService,
                            VorteilService vorteilService,
                            VoraussetzungService voraussetzungService,
                            ZauberService zauberService,
                            ZaubertrickService zaubertrickService,
                            ZeremonieService zeremonieService,
                            LoggerService loggerService) {
        this.kampftechnikService = kampftechnikService;
        this.parserService = parserService;
        this.attributeService = attributeService;
        this.liturgieService = liturgieService;
        this.ritualService = ritualService;
        this.sonderfertigkeitService = sonderfertigkeitService;
        this.segenService = segenService;
        this.vorteilService = vorteilService;
        this.voraussetzungService = voraussetzungService;
        this.zauberService = zauberService;
        this.zaubertrickService = zaubertrickService;
        this.zeremonieService = zeremonieService;
        this.loggerService = loggerService;
    }

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

    private <T extends Attribut> List<T> persist(AbstractAttributService<T> service, ParsedData<T> parsedData) {
        if (parsedData.isEmpty()) {
            loggerService.warn(getClass(), "No data found for current URL");
            return Collections.emptyList();
        }

        T persistedParent = persistParent(service, parsedData.getAttribut());

        return parsedData.getChildren().stream()
                .filter(Objects::nonNull)
                .filter(value -> value.getName().length() > 0)
                .filter(value -> value.getSubcategoryOf() != null || service.findByName(value.getName()) == null)
                .map(value -> persistChild(service, persistedParent, value))
                .collect(Collectors.toList());
    }

    private <T extends Attribut> T persistChild(@NotNull AbstractAttributService<T> service, T persistedParent, @NotNull T attribut) {
        if (persistedParent.getId() == null || persistedParent.getId() == 0) {
            throw new IllegalStateException(
                    String.format("persistedParent should have an ID != 0. Value: %s",
                            persistedParent.toString()));
        }

        T persistedChild = service.findByNameAndSubcategoryOf(attribut.getName(), persistedParent);

        if (persistedChild != null) return persistedChild;

        attribut.setSubcategoryOf(persistedParent);
        loggerService.info(getClass(),
                String.format("persisting new %s named \"%s\" referencing %s",
                        attribut.getClass().getSimpleName(), attribut.getName(), persistedParent.getName()));
        return service.save(attribut);
    }

    private <T extends Attribut> T persistParent(@NotNull AbstractAttributService<T> service, @NotNull T attribut) {
        T persistedAttribute = service.findByName(attribut.getName());

        if (persistedAttribute == null) {
            return service.save(attribut);
        }
        return persistedAttribute;
    }

    public List<Kampftechnik> parseKampftechnik(String listUrl, boolean isFernkampf) {
        loggerService.debug(getClass(), "parsing Kampftechnik");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseKampftechnik(url, isFernkampf))
                .map(x -> persist(kampftechnikService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Liturgie> parseLiturgie(String listUrl) {
        loggerService.debug(getClass(), "parsing Liturgie");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseLiturgie)
                .map(x -> persist(liturgieService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Ritual> parseRitual(String listUrl) {
        loggerService.debug(getClass(), "parsing Ritual");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseRitual)
                .map(x -> persist(ritualService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Sonderfertigkeit> parseSonderfertigkeit(Sonderfertigkeit.Gruppe gruppe, String listUrl) {
        loggerService.debug(getClass(), "parsing Sonderfertigkeit");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseSonderfertigkeit(url, gruppe))
                .map(x -> persist(sonderfertigkeitService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Segen> parseSegen(String listUrl) {
        loggerService.debug(getClass(), "parsing Segen");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseSegen)
                .map(x -> persist(segenService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Vorteil> parseVorteil(String listUrl) {
        loggerService.debug(getClass(), "parsing Vorteil");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseVorteil)
                .map(x -> persist(vorteilService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Zauber> parseZauber(String listUrl) {
        loggerService.debug(getClass(), "parsing Zauber");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseZauber)
                .map(x -> persist(zauberService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Zaubertrick> parseZaubertrick(String listUrl) {
        loggerService.debug(getClass(), "parsing Zaubertrick");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseZaubertrick)
                .map(x -> persist(zaubertrickService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Zeremonie> parseZeremonie(String listUrl) {
        loggerService.debug(getClass(), "parsing Zeremonie");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseZeremonie)
                .map(x -> persist(zeremonieService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void parseVorraussetzungen() {
        loggerService.debug(getClass(), "parsing Vorraussetzungen");
        Iterable<Attribut> attributs = attributeService.find(null, null, false);

        for (Attribut attribut : attributs) {
            voraussetzungService.parseVoraussetzung(attribut);
        }
    }

}
