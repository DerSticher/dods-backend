package io.dods.parser;

import io.dods.model.attribute.*;
import io.dods.parser.misc.DocumentService;
import io.dods.parser.model.ParsedData;
import io.dods.parser.model.ParsedValue;
import io.dods.parser.references.ParseReferenceService;
import io.dods.parser.references.ParseValueLevelService;
import io.dods.parser.valueParser.*;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
class ParserService {

    private final ParseValueLevelService parseValueLevelService;

    private final ParseReferenceService parseReferenceService;

    private final ParseApWertService parseApWertService;

    private final ParseNameService parseNameService;

    private final ParseDauerService parseDauerService;

    private final ParseKostentabelleService parseKostentabelleService;

    private final ParseProbeService parseProbeService;

    private final ParseReichweiteService parseReichweiteService;

    private final ParseZielkategorieService parseZielkategorieService;

    private final ParseWirkungService parseWirkungService;

    private final ParseNutzkostenService parseNutzkostenService;

    private final ParseAspektService parseAspektService;

    private final DocumentService documentService;

    private final ParseLeiteigenschaftService parseLeiteigenschaftService;

    private final ParsePublikationService parsePublikationServiceService;

    @Autowired
    public ParserService(ParseDauerService parseDauerService, ParseValueLevelService parseValueLevelService, ParseReferenceService parseReferenceService, ParseLeiteigenschaftService parseLeiteigenschaftService, ParseApWertService parseApWertService, ParseNameService parseNameService, ParseKostentabelleService parseKostentabelleService, ParseProbeService parseProbeService, ParseReichweiteService parseReichweiteService, DocumentService documentService, ParseAspektService parseAspektService, ParseZielkategorieService parseZielkategorieService, ParseWirkungService parseWirkungService, ParseNutzkostenService parseNutzkostenService, ParsePublikationService parsePublikationServiceService) {
        this.parseDauerService = parseDauerService;
        this.parseValueLevelService = parseValueLevelService;
        this.parseReferenceService = parseReferenceService;
        this.parseLeiteigenschaftService = parseLeiteigenschaftService;
        this.parseApWertService = parseApWertService;
        this.parseNameService = parseNameService;
        this.parseKostentabelleService = parseKostentabelleService;
        this.parseProbeService = parseProbeService;
        this.parseReichweiteService = parseReichweiteService;
        this.documentService = documentService;
        this.parseAspektService = parseAspektService;
        this.parseZielkategorieService = parseZielkategorieService;
        this.parseWirkungService = parseWirkungService;
        this.parseNutzkostenService = parseNutzkostenService;
        this.parsePublikationServiceService = parsePublikationServiceService;
    }

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

    public ParsedData<Kampftechnik> parseKampftechnik(String url, boolean isFernkampf) {
        try {
            return parseSimple(url, value -> new Kampftechnik(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getLeiteigenschaft(),
                    value.getKostentabelle(),
                    value.getName(),
                    isFernkampf));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Liturgie> parseLiturgie(String url) {
        try {
            return parseSimple(url, value -> new Liturgie(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getDauer(),
                    value.getKostentabelle(),
                    value.getReichweite(),
                    value.getNutzkosten(),
                    value.getProbe(),
                    value.getWirkungsdauer(),
                    value.getZielkategorie(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Ritual> parseRitual(String url) {
        try {
            return parseSimple(url, value -> new Ritual(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getNutzkosten(),
                    value.getDauer(),
                    value.getKostentabelle(),
                    value.getReichweite(),
                    value.getProbe(),
                    value.getWirkungsdauer(),
                    value.getZielkategorie(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Sonderfertigkeit> parseSonderfertigkeit(String url, Sonderfertigkeit.Gruppe gruppe) {
        try {
            return parseSimple(url, value -> new Sonderfertigkeit(
                        value.getWikiUrl(),
                        value.getPublikations(),
                        value.getApWert(),
                        gruppe,
                        value.getDauer(),
                        value.getNutzkosten(),
                        value.getProbe(),
                        value.getReichweite(),
                        value.getWirkungsdauer(),
                        value.getKostentabelle(),
                        value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Segen> parseSegen(String url) {
        try {
            return parseSimple(url, value -> new Segen(
                value.getWikiUrl(),
                value.getPublikations(),
                value.getAspekt(),
                value.getNutzkosten(),
                value.getReichweite(),
                value.getWirkungsdauer(),
                value.getZielkategorie(),
                value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Vorteil> parseVorteil(String url) {
        try {
            return parseSimple(url, value -> new Vorteil(
                        value.getWikiUrl(),
                        value.getPublikations(),
                        value.getApWert(),
                        value.getReichweite(),
                        value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Zauber> parseZauber(String url) {
        try {
            return parseSimple(url, value -> new Zauber(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getNutzkosten(),
                    value.getKostentabelle(),
                    value.getProbe(),
                    value.getReichweite(),
                    value.getWirkungsdauer(),
                    value.getDauer(),
                    value.getZielkategorie(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Zaubertrick> parseZaubertrick(String url) {
        try {
            return parseSimple(url, value -> new Zaubertrick(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getReichweite(),
                    value.getZielkategorie(),
                    value.getWirkungsdauer(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    public ParsedData<Zeremonie> parseZeremonie(String url) {
        try {
            return parseSimple(url, value -> new Zeremonie(
                    value.getWikiUrl(),
                    value.getPublikations(),
                    value.getNutzkosten(),
                    value.getProbe(),
                    value.getReichweite(),
                    value.getDauer(),
                    value.getWirkungsdauer(),
                    value.getKostentabelle(),
                    value.getZielkategorie(),
                    value.getName()));
        } catch (IOException e) {
            return null;
        }
    }

    private interface ParseCallback<T extends Attribut> {
        T parse(ParsedValue parsedValue);
    }

    public <T extends Attribut> ParsedData<T> parseSimple(String url, ParseCallback<T> parseCallback) throws IOException {
        ParsedValue parsedValue = parseDetails(url);

        List<ParsedValue> parsedValues = new ArrayList<>();
        parsedValues.addAll(parseValueLevelService.checkForLevels(parsedValue));
        parsedValues.addAll(parseReferenceService.checkForSubcategories(parsedValue));

        List<T> collect = parsedValues.stream()
                .map(parseCallback::parse)
                .collect(Collectors.toList());

        parseValueLevelService.setRealName(parsedValue);

        T data = parseCallback.parse(parsedValue);

        return new ParsedData<>(data, collect);
    }
}
