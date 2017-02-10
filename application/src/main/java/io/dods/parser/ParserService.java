package io.dods.parser;

import io.dods.model.attribute.*;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
class ParserService {

    @Autowired
    private ParseValueLevelService parseValueLevelService;

    @Autowired
    private ParseReferenceService parseReferenceService;

    @Autowired
    private ParseApWertService parseApWertService;

    @Autowired
    private ParseNameService parseNameService;

    @Autowired
    private ParseDauerService parseDauerService;

    @Autowired
    private ParseKostentabelleService parseKostentabelleService;

    @Autowired
    private ParseProbeService parseProbeService;

    @Autowired
    private ParseReichweiteService parseReichweiteService;

    @Autowired
    private ParseZielkategorieService parseZielkategorieService;

    @Autowired
    private ParseWirkungService parseWirkungService;

    @Autowired
    private ParseNutzkostenService parseNutzkostenService;

    @Autowired
    private ParseAspektService parseAspektService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ParseLeiteigenschaftService parseLeiteigenschaftService;

    @Autowired
    private ParsePublikationService parsePublikationServiceService;

    private ParsedValue parseDetails(String url) throws IOException {
        ParsedValue value = new ParsedValue();

        Document document = documentService.getDocument(url, "http://www.ulisses-regelwiki.de/");

        value.setWikiUrl(url);
        value.setName(parseNameService.parseName(document));
        value.setReichweite(parseReichweiteService.parseReichweite(document));
        value.setNutzkosten(parseNutzkostenService.parseNutzkosten(document));

        value.setApWert(parseApWertService.parseApWert(document));
        value.setDauer(parseDauerService.parseDauer(document));
        value.setKostentabelle(parseKostentabelleService.parseKostentabelle(document));
        value.setProbe(parseProbeService.parseProbe(document));
        value.setWirkungsdauer(parseWirkungService.parseWirkungsdauer(document));
        value.setZielkategorie(parseZielkategorieService.parseZielkategorie(document));
        value.setAspekt(parseAspektService.parseAspekt(document));
        value.setLeiteigenschaft(parseLeiteigenschaftService.parseLeiteigenschaft(document));
        value.setPublikations(parsePublikationServiceService.parsePublikations(document));

        return value;
    }

    public Kampftechnik parseKampftechnik(String url, boolean isFernkampf) {
        try {
            ParsedValue value = parseDetails(url);

            return new Kampftechnik(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getLeiteigenschaft(),
                    value.getKostentabelle(),
                    value.getName(),
                    isFernkampf);
        } catch (IOException e) {
            return null;
        }
    }

    public Liturgie parseLiturgie(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Liturgie(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getDauer(),
                    value.getKostentabelle(),
                    value.getReichweite(),
                    value.getNutzkosten(),
                    value.getProbe(),
                    value.getWirkungsdauer(),
                    value.getZielkategorie(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }

    public Ritual parseRitual(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Ritual(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getNutzkosten(),
                    value.getDauer(),
                    value.getKostentabelle(),
                    value.getReichweite(),
                    value.getProbe(),
                    value.getWirkungsdauer(),
                    value.getZielkategorie(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }

    public List<Sonderfertigkeit> parseSonderfertigkeit(String url, Sonderfertigkeit.Gruppe gruppe) {
        try {
            ParsedValue value = parseDetails(url);

            List<ParsedValue> parsedValues = new ArrayList<>();
            parsedValues.addAll(parseValueLevelService.checkForLevels(value));
            parsedValues.addAll(parseReferenceService.checkForSubcategories(value));

            List<Sonderfertigkeit> sonderfertigkeiten = new ArrayList<>();

            for (int i = 0; i < parsedValues.size(); i++) {
                ParsedValue v = parsedValues.get(i);

                Sonderfertigkeit sonderfertigkeit = new Sonderfertigkeit(
                        v.getWikiUrl(),
                        value.getPublikations(),
                        v.getApWert(),
                        gruppe,
                        v.getDauer(),
                        v.getNutzkosten(),
                        v.getProbe(),
                        v.getReichweite(),
                        v.getWirkungsdauer(),
                        v.getKostentabelle(),
                        v.getName());

                sonderfertigkeiten.add(sonderfertigkeit);

                if (i > 0) {
                    sonderfertigkeit.setSubcategoryOf(sonderfertigkeiten.get(0));
                }
            }

            return sonderfertigkeiten;
        } catch (IOException e) {
            return null;
        }
    }

    public Segen parseSegen(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Segen(
                value.getWikiUrl(),
                value.getPublikations(),
                value.getAspekt(),
                value.getNutzkosten(),
                value.getReichweite(),
                value.getWirkungsdauer(),
                value.getZielkategorie(),
                value.getName());
        } catch (IOException e) {
            return null;
        }
    }

    public List<Vorteil> parseVorteil(String url) {
        try {
            ParsedValue value = parseDetails(url);

            List<ParsedValue> parsedValues = new ArrayList<>();
            parsedValues.addAll(parseValueLevelService.checkForLevels(value));
            parsedValues.addAll(parseReferenceService.checkForSubcategories(value));

            List<Vorteil> vorteile = new ArrayList<>();

            for (int i = 0; i < parsedValues.size(); i++) {
                ParsedValue v = parsedValues.get(i);

                Vorteil vorteil = new Vorteil(
                        value.getWikiUrl(),
                        value.getPublikations(),
                        v.getApWert(),
                        v.getReichweite(),
                        v.getName());

                vorteile.add(vorteil);

                if (i > 0) {
                    vorteil.setSubcategoryOf(vorteile.get(0));
                }
            }

            return vorteile;
        } catch (IOException e) {
            return null;
        }
    }

    public Zauber parseZauber(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Zauber(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getNutzkosten(),
                    value.getKostentabelle(),
                    value.getProbe(),
                    value.getReichweite(),
                    value.getWirkungsdauer(),
                    value.getDauer(),
                    value.getZielkategorie(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }

    public Zaubertrick parseZaubertrick(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Zaubertrick(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getReichweite(),
                    value.getZielkategorie(),
                    value.getWirkungsdauer(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }

    public Zeremonie parseZeremonie(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Zeremonie(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getNutzkosten(),
                    value.getProbe(),
                    value.getReichweite(),
                    value.getDauer(),
                    value.getWirkungsdauer(),
                    value.getKostentabelle(),
                    value.getZielkategorie(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }
}
