package io.dods.services.parser;

import io.dods.model.properties.*;
import io.dods.services.misc.LoggerService;
import io.dods.services.parser.model.ParsedData;
import io.dods.services.parser.valueParser.CreateDependencyService;
import io.dods.services.parser.valueParser.ParseDependencyService;
import io.dods.services.properties.AbstractPropertyService;
import io.dods.services.properties.combatTechnique.CombatTechniqueService;
import io.dods.services.properties.liturgicalChant.LiturgicalChantService;
import io.dods.services.properties.property.PropertyService;
import io.dods.services.properties.ritual.RitualService;
import io.dods.services.properties.bless.BlessService;
import io.dods.services.properties.specialAbility.SpecialAbilityService;
import io.dods.services.properties.advantage.AdvantageService;
import io.dods.services.properties.spell.SpellService;
import io.dods.services.properties.cantrip.CantripService;
import io.dods.services.properties.ceremony.CeremonyService;
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

    private final PropertyService propertyService;

    private final CombatTechniqueService combatTechniqueService;

    private final LiturgicalChantService liturgicalChantService;

    private final RitualService ritualService;

    private final SpecialAbilityService specialAbilityService;

    private final BlessService blessService;

    private final AdvantageService advantageService;

    private final SpellService spellService;

    private final CantripService cantripService;

    private final CeremonyService ceremonyService;

    private final ParseDependencyService voraussetzungService;

    private final LoggerService loggerService;

    private final CreateDependencyService createDependencyService;

    @Autowired
    public ParseListService(ParserService parserService,
                            CombatTechniqueService combatTechniqueService,
                            PropertyService propertyService,
                            LiturgicalChantService liturgicalChantService,
                            RitualService ritualService,
                            SpecialAbilityService specialAbilityService,
                            BlessService blessService,
                            AdvantageService advantageService,
                            ParseDependencyService voraussetzungService,
                            SpellService spellService,
                            CantripService cantripService,
                            CeremonyService ceremonyService,
                            LoggerService loggerService,
                            CreateDependencyService createDependencyService) {
        this.combatTechniqueService = combatTechniqueService;
        this.parserService = parserService;
        this.propertyService = propertyService;
        this.liturgicalChantService = liturgicalChantService;
        this.ritualService = ritualService;
        this.specialAbilityService = specialAbilityService;
        this.blessService = blessService;
        this.advantageService = advantageService;
        this.voraussetzungService = voraussetzungService;
        this.spellService = spellService;
        this.cantripService = cantripService;
        this.ceremonyService = ceremonyService;
        this.loggerService = loggerService;
        this.createDependencyService = createDependencyService;
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

    private <T extends Property> List<T> persist(AbstractPropertyService<T> service, ParsedData<T> parsedData) {
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

    private <T extends Property> T persistChild(@NotNull AbstractPropertyService<T> service, T persistedParent, @NotNull T attribut) {
        if (persistedParent.getId() == null || persistedParent.getId() == 0) {
            throw new IllegalStateException(
                    String.format("persistedParent should have an ID != 0. Value: %s",
                            persistedParent.toString()));
        }

        T persistedChild = service.findByNameAndSubcategoryOf(attribut.getName(), persistedParent);

        if (persistedChild != null) {
            // check for update
            update(persistedChild, attribut);
            service.save(persistedChild);
            return persistedChild;
        }

        attribut.setSubcategoryOf(persistedParent);
        loggerService.info(getClass(),
                String.format("persisting new %s named \"%s\" referencing %s",
                        attribut.getClass().getSimpleName(), attribut.getName(), persistedParent.getName()));
        return service.save(attribut);
    }

    private <T extends Property> void update(@NotNull T persistedProperty, @NotNull T parsedProperty) {
        persistedProperty.update(parsedProperty);
    }

    private <T extends Property> T persistParent(@NotNull AbstractPropertyService<T> service, @NotNull T attribut) {
        T persistedAttribute = service.findByName(attribut.getName());

        if (persistedAttribute == null) {
            return service.save(attribut);
        }
        return persistedAttribute;
    }

    public List<CombatTechnique> parseCombatTechnique(String listUrl, boolean isRangedCombat) {
        loggerService.debug(getClass(), "parsing CombatTechnique");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseCombatTechnique(url, isRangedCombat))
                .map(x -> persist(combatTechniqueService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<LiturgicalChant> parseLiturgicalChant(String listUrl) {
        loggerService.debug(getClass(), "parsing LiturgicalChant");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseLiturgicalChant)
                .map(x -> persist(liturgicalChantService, x))
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

    public List<SpecialAbility> parseSpecialAbility(SpecialAbility.Group group, String listUrl) {
        loggerService.debug(getClass(), "parsing SpecialAbility");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(url -> parserService.parseSpecialAbility(url, group))
                .map(x -> persist(specialAbilityService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Bless> parseBless(String listUrl) {
        loggerService.debug(getClass(), "parsing Bless");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseBless)
                .map(x -> persist(blessService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Advantage> parseAdvantage(String listUrl) {
        loggerService.debug(getClass(), "parsing Advantage");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseAdvantage)
                .map(x -> persist(advantageService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Spell> parseSpell(String listUrl) {
        loggerService.debug(getClass(), "parsing Spell");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseSpell)
                .map(x -> persist(spellService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Cantrip> parseCantrip(String listUrl) {
        loggerService.debug(getClass(), "parsing Cantrip");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseCantrip)
                .map(x -> persist(cantripService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Ceremony> parseCeremony(String listUrl) {
        loggerService.debug(getClass(), "parsing Ceremony");
        List<String> urls = searchLinks(listUrl);

        return urls.stream()
                .map(parserService::parseCeremony)
                .map(x -> persist(ceremonyService, x))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public void parseDependencies() {
        loggerService.debug(getClass(), "parsing Dependencies");
        Iterable<Property> attributes = propertyService.find(null, null, false);

        for (Property property : attributes) {
            createDependencyService.parseDependency(property);
        }
    }

}
