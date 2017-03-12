package io.dods.services.parser.valueParser;

import io.dods.model.conditions.level.MinLevelCondition;
import io.dods.model.conditions.level.check.FixedLevelCheck;
import io.dods.model.conditions.lists.AndCondition;
import io.dods.model.conditions.lists.OrCondition;
import io.dods.model.properties.Property;
import io.dods.model.rules.Effect;
import io.dods.services.dependency.DependencyService;
import io.dods.api.exceptions.ResourceNotFoundException;
import io.dods.services.properties.property.PropertyService;
import io.dods.model.conditions.*;
import io.dods.model.rules.Dependency;
import io.dods.services.parser.misc.DocumentService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Richard Gottschalk
 */
@Service
public class CreateDependencyService {

    private static final Pattern PATTERN = Pattern.compile(" ?([\\wäöüÄÖÜß \\(\\)]+)", Pattern.MULTILINE);

    private final PropertyService propertyService;

    private final ParseDependencyService parseDependencyService;

    private final DocumentService documentService;

    private final DependencyService dependencyService;

    @Autowired
    public CreateDependencyService(PropertyService propertyService,
                                   ParseDependencyService parseDependencyService,
                                   DocumentService documentService,
                                   DependencyService dependencyService) {
        this.propertyService = propertyService;
        this.parseDependencyService = parseDependencyService;
        this.documentService = documentService;
        this.dependencyService = dependencyService;
    }

    public void parseDependency(Property property) {
        if (hasRule(property)) return;

        try {
            String wikiUrl = property.getWikiUrl();
            if (wikiUrl == null) {
                return;
            }
            Document document = documentService.getDocument(wikiUrl, "http://www.ulisses-regelwiki.de/");

            String voraussetzung = parseDependencyService.parseDependency(document);
            if (voraussetzung == null || voraussetzung.length() == 0) return;

            Dependency dependency = createAbhangigkeit(property, voraussetzung);
            if (dependency != null) dependencyService.save(dependency);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean hasRule(Property property) {
        try {
            dependencyService.findByEffectProperty(property);
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    private Dependency createAbhangigkeit(Property effect, String voraussetzung) {
        Dependency dependency = new Dependency();

        dependency.setEffect(new Effect(effect));

        Condition condition = parseBedingung(voraussetzung);
        if (condition == null) return null;

        dependency.setCondition(condition);

        return dependency;
    }

    @Nullable
    private Condition parseBedingung(String voraussetzung) {
        if (voraussetzung.equals("keine")) return null;

        Matcher matcher = PATTERN.matcher(voraussetzung);

        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        if (matches.size() > 1) {
            return parseMultiple(matches);
        } else if (matches.size() == 1){
            return parseSingle(matches.get(0));
        }

        return null;
    }

    private Condition parseMultiple(List<String> matches) {
        AndCondition andBedingung = new AndCondition();
        List<Condition> bedingungenBuffer = new ArrayList<>();

        boolean not = false;

        for (String match : matches) {
            not |= match.contains("kein ");
            if (match.contains("kein ")) {
                match = match.replace("kein ", "").trim();
            }
            match = match.trim();

            if (match.contains(" oder ")) {
                String[] split = match.split(" oder ");
                for (String subelement : split) {
                    Condition condition = parseSingle(subelement);
                    if (condition != null) bedingungenBuffer.add(condition);
                }
                Condition orCondition = createOrBedingungFromList(not, bedingungenBuffer);
                andBedingung.add(orCondition);

                bedingungenBuffer.clear();
                not = false;
            } else {
                Condition condition = parseSingle(match);
                if (condition != null) {
                    if (not) condition = new NotCondition(condition);
                    bedingungenBuffer.add(condition);
                }
            }
        }
        if (andBedingung.getConditions().size() == 1) {
            return andBedingung.getConditions().get(0);
        }

        for (Condition condition : bedingungenBuffer) {
            andBedingung.add(condition);
        }
        return andBedingung;
    }

    private Condition createOrBedingungFromList(boolean not, List<Condition> bedingungenBuffer) {
        OrCondition orBedingung = new OrCondition();

        for (Condition condition : bedingungenBuffer) {
            orBedingung.add(condition);
        }

        if (not) {
            return new NotCondition(orBedingung);
        }
        return orBedingung;
    }

    private AndCondition parseAnd(List<String> matches) {
        AndCondition andBedingung = new AndCondition();

        for (int i = 0; i < matches.size(); i++) {
            Condition condition = parseSingle(matches.get(i));
            if (condition != null) andBedingung.add(condition);
        }

        return andBedingung;
    }

    private Condition parseSingle(String text) {
        text = text.replaceAll("(?:Vorteil|Nachteil|Sonderfertigkeit|Kampftechnik) ", "").trim();

        String[] split = text.split(" ");
        List<String> strings = Arrays.asList(split);

        if (strings.size() > 0 && strings.get(0).equalsIgnoreCase("kein")) {
            text = listToString(strings.subList(1, strings.size()));
            Property property = findAttribut(text);

            if (property != null) {
                Condition not = new HasPropertyCondition(property);
                return new NotCondition(not);
            }
        } else if (strings.size() > 0){
            Property property = findAttribut(text);
            if (property != null) {
                text = text.replace(property.getName(), "").trim();

                if (text.length() > 0) {
                    try {
                        int level = Integer.parseInt(strings.get(strings.size() - 1));
                        return new MinLevelCondition(property, new FixedLevelCheck(level), 0);
                    } catch (NumberFormatException ignore) {
                        Property subProperty = findAttribut(text);
                        if (subProperty != null) {
                            return new HasPropertyCondition(subProperty);
                        }
                        return new HasPropertyCondition(property);
                    }
                } else {
                    return new HasPropertyCondition(property);
                }
            }
        }

        return null;
    }

    private String listToString(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < stringList.size(); i++) {
            if (i > 0) stringBuilder.append(" ");
            String s = stringList.get(i);
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }

    @Nullable
    private Property findAttribut(String text) {
        String[] split = text.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(split[i]);

            Property property = identifyAttribut(sb.toString());
            if (property != null) {
                return findSubAttribut(property, text);
            }
        }

        return null;
    }

    @NotNull
    private Property findSubAttribut(Property property, String fullText) {
        fullText = fullText.replace(property.getName(), "");
        fullText = fullText.replaceAll("[\\(\\)]", "");

        List<Property> subcategories = propertyService.findSubcategoriesById(property.getId());

        String[] split = fullText.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : split) {
            sb.append(word);
            if (subcategories != null) {
                for (Property sub : subcategories) {
                    if (sub.getName().equals(sb.toString())) {
                        return sub;
                    }
                }

            }
        }

        return property;
    }

    @Nullable
    private Property identifyAttribut(String token) {
        if (token.equals("MU")) return propertyService.findFirstByName("Mut");
        if (token.equals("KL")) return propertyService.findFirstByName("Klugheit");
        if (token.equals("IN")) return propertyService.findFirstByName("Intuition");
        if (token.equals("CH")) return propertyService.findFirstByName("Charisma");
        if (token.equals("FF")) return propertyService.findFirstByName("Fingerfertigkeit");
        if (token.equals("GE")) return propertyService.findFirstByName("Gewandtheit");
        if (token.equals("KO")) return propertyService.findFirstByName("Konstitution");
        if (token.equals("KK")) return propertyService.findFirstByName("Körperkraft");

        Iterable<Property> attributs = propertyService.find(null, token, false);

        for (Property property : attributs) {
            return property;
        }
        return null;
    }

}
