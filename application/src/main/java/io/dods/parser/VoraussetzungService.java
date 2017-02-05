package io.dods.parser;

import io.dods.abhaengigkeitService.AbhangigkeitService;
import io.dods.attributeService.attribute.AttributeService;
import io.dods.model.attribute.Attribut;
import io.dods.model.bedingungen.*;
import io.dods.model.regeln.Abhangigkeit;
import io.dods.model.regeln.Effekt;
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
class VoraussetzungService {

    private static final Pattern PATTERN = Pattern.compile(" ?([\\wäöüÄÖÜß \\(\\)]+)", Pattern.MULTILINE);

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ParseVoraussetzungService parseVoraussetzungService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AbhangigkeitService abhangigkeitService;

    public void parseNewVoraussetzung(Attribut attribut) {
        if (hasRule(attribut)) return;

        try {
            String wikiUrl = attribut.getWikiUrl();
            if (wikiUrl == null) {
                return;
            }
            Document document = documentService.getDocument(wikiUrl, "http://www.ulisses-regelwiki.de/");

            String voraussetzung = parseVoraussetzungService.parseVoraussetzung(document);
            if (voraussetzung == null || voraussetzung.length() == 0) return;

            Abhangigkeit abhangigkeit = createAbhangigkeit(attribut, voraussetzung);
            if (abhangigkeit != null) abhangigkeitService.save(abhangigkeit);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean hasRule(Attribut attribut) {
        Abhangigkeit abhangigkeit = abhangigkeitService.findByEffektAttribut(attribut);
        return abhangigkeit != null;
    }

    private Abhangigkeit createAbhangigkeit(Attribut effect, String voraussetzung) {
        Abhangigkeit abhangigkeit = new Abhangigkeit();

        abhangigkeit.setEffekt(new Effekt(effect, 1));

        Bedingung bedingung = parseBedingung(voraussetzung);
        if (bedingung == null) return null;

        abhangigkeit.setBedingung(bedingung);

        return abhangigkeit;
    }

    @Nullable
    private Bedingung parseBedingung(String voraussetzung) {
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

    private Bedingung parseMultiple(List<String> matches) {
        AndBedingung andBedingung = new AndBedingung();
        List<Bedingung> bedingungenBuffer = new ArrayList<>();

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
                    Bedingung bedingung = parseSingle(subelement);
                    if (bedingung != null) bedingungenBuffer.add(bedingung);
                }
                Bedingung orBedingung = createOrBedingungFromList(not, bedingungenBuffer);
                andBedingung.add(orBedingung);

                bedingungenBuffer.clear();
                not = false;
            } else {
                Bedingung bedingung = parseSingle(match);
                if (bedingung != null) {
                    if (not) bedingung = new NotBedingung(bedingung);
                    bedingungenBuffer.add(bedingung);
                }
            }
        }
        if (andBedingung.getAnd().size() == 1) {
            return andBedingung.getAnd().get(0);
        }

        for (Bedingung bedingung : bedingungenBuffer) {
            andBedingung.add(bedingung);
        }
        return andBedingung;
    }

    private Bedingung createOrBedingungFromList(boolean not, List<Bedingung> bedingungenBuffer) {
        OrBedingung orBedingung = new OrBedingung();

        for (Bedingung bedingung : bedingungenBuffer) {
            orBedingung.add(bedingung);
        }

        if (not) {
            return new NotBedingung(orBedingung);
        }
        return orBedingung;
    }

    private AndBedingung parseAnd(List<String> matches) {
        AndBedingung andBedingung = new AndBedingung();

        for (int i = 0; i < matches.size(); i++) {
            Bedingung bedingung = parseSingle(matches.get(i));
            if (bedingung != null) andBedingung.add(bedingung);
        }

        return andBedingung;
    }

    private Bedingung parseSingle(String text) {
        text = text.replaceAll("(?:Vorteil|Nachteil|Sonderfertigkeit|Kampftechnik) ", "").trim();

        String[] split = text.split(" ");
        List<String> strings = Arrays.asList(split);

        if (strings.size() > 0 && strings.get(0).equalsIgnoreCase("kein")) {
            text = listToString(strings.subList(1, strings.size()));
            Attribut attribut = findAttribut(text);

            if (attribut != null) {
                Bedingung not = new VorhandenBedingung(attribut);
                return new NotBedingung(not);
            }
        } else if (strings.size() > 0){
            Attribut attribut = findAttribut(text);
            if (attribut != null) {
                text = text.replace(attribut.getName(), "").trim();

                if (text.length() > 0) {
                    try {
                        int level = Integer.parseInt(strings.get(strings.size() - 1));
                        return new MinBedingung(attribut, level);
                    } catch (NumberFormatException ignore) {
                        Attribut subAttribut = findAttribut(text);
                        if (subAttribut != null) {
                            return new VorhandenBedingung(subAttribut);
                        }
                        return new VorhandenBedingung(attribut);
                    }
                } else {
                    return new VorhandenBedingung(attribut);
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
    private Attribut findAttribut(String text) {
        String[] split = text.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(split[i]);

            Attribut attribut = identifyAttribut(sb.toString());
            if (attribut != null) {
                return findSubAttribut(attribut, text);
            }
        }

        return null;
    }

    @NotNull
    private Attribut findSubAttribut(Attribut attribut, String fullText) {
        fullText = fullText.replace(attribut.getName(), "");
        fullText = fullText.replaceAll("[\\(\\)]", "");

        List<Attribut> subcategories = attributeService.findSubcategoriesById(attribut.getId());

        String[] split = fullText.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : split) {
            sb.append(word);
            if (subcategories != null) {
                for (Attribut sub : subcategories) {
                    if (sub.getName().equals(sb.toString())) {
                        return sub;
                    }
                }

            }
        }

        return attribut;
    }

    @Nullable
    private Attribut identifyAttribut(String token) {
        if (token.equals("MU")) return attributeService.findFirstByName("Mut");
        if (token.equals("KL")) return attributeService.findFirstByName("Klugheit");
        if (token.equals("IN")) return attributeService.findFirstByName("Intuition");
        if (token.equals("CH")) return attributeService.findFirstByName("Charisma");
        if (token.equals("FF")) return attributeService.findFirstByName("Fingerfertigkeit");
        if (token.equals("GE")) return attributeService.findFirstByName("Gewandtheit");
        if (token.equals("KO")) return attributeService.findFirstByName("Konstitution");
        if (token.equals("KK")) return attributeService.findFirstByName("Körperkraft");

        Iterable<Attribut> attributs = attributeService.find(null, token, false);

        for (Attribut attribut : attributs) {
            return attribut;
        }
        return null;
    }

}
