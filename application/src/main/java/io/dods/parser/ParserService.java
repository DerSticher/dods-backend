package io.dods.parser;

import io.dods.model.attribute.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
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
    private ParseKapKostenService parseKapKostenService;

    @Autowired
    private ParseNutzkostenService parseNutzkostenService;

    @Autowired
    private ParseAspektService parseAspektService;

    private ParsedValue parseDetails(String url) throws IOException {
        ParsedValue value = new ParsedValue();

        Document document = getDocument(url, "http://www.ulisses-regelwiki.de/");

        value.setName(parseNameService.parseName(document));
        value.setReichweite(parseReichweiteService.parseReichweite(document));
        value.setNutzkosten(parseNutzkostenService.parseNutzkosten(document));

        value.setApWert(parseApWertService.parseApWert(document));
        value.setDauer(parseDauerService.parseDauer(document));
        value.setKostentabelle(parseKostentabelleService.parseKostentabelle(document));
        value.setProbe(parseProbeService.parseProbe(document));
        value.setWirkung(parseWirkungService.parseWirkung(document));
        value.setWirkungsdauer(parseWirkungService.parseWirkungsdauer(document));
        value.setZielkategorie(parseZielkategorieService.parseZielkategorie(document));
        value.setAspekt(parseAspektService.parseAspekt(document));

        return value;
    }

    private Document getDocument(String url, String baseUrl) throws IOException {
        String currentFolder = System.getProperty("user.dir");
        String localFileName = url.replace(":", "").replace("?", "_");
        if (!localFileName.endsWith(".html")) localFileName += ".html";
        File bufferedFile = new File(currentFolder + "/_buffer", localFileName);

        if (bufferedFile.exists() && !localFileName.contains("?")) {
            System.out.println("Parsing local " + url);
            return Jsoup.parse(bufferedFile, "UTF-8", baseUrl);
        } else {
            System.out.println("Parsing remote " + url);
            Document document = Jsoup.connect(url).get();

            bufferedFile.getParentFile().mkdirs();

            FileWriter fileWriter = new FileWriter(bufferedFile);
            fileWriter.write(document.toString());
            fileWriter.close();

            try {
                // sleeping to reduce traffic and avoid HTTP 429
                Thread.sleep((long) (Math.random() * 1000 * 20 + 5 * 1000));
            } catch (InterruptedException ignore) {}

            return document;
        }
    }

    public Liturgie parseLiturgie(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Liturgie(
                    value.getDauer(),
                    value.getKostentabelle(),
                    value.getReichweite(),
                    value.getNutzkosten(),
                    value.getProbe(),
                    value.getWirkung(),
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
                    value.getNutzkosten(),
                    value.getDauer(),
                    value.getKostentabelle(),
                    value.getReichweite(),
                    value.getProbe(),
                    value.getWirkung(),
                    value.getWirkungsdauer(),
                    value.getZielkategorie(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }

    public Segen parseSegen(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Segen(
                value.getAspekt(),
                value.getNutzkosten(),
                value.getReichweite(),
                value.getWirkung(),
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

            List<ParsedValue> parsedValues = parseValueLevelService.checkForLevels(value);

            List<Vorteil> vorteile = new ArrayList<>();

            for (int i = 0; i < parsedValues.size(); i++) {
                ParsedValue v = parsedValues.get(i);

                Vorteil vorteil = new Vorteil(
                        v.getApWert(),
                        v.getWirkung(),
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
                    value.getNutzkosten(),
                    value.getKostentabelle(),
                    value.getProbe(),
                    value.getReichweite(),
                    value.getWirkung(),
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
                    value.getReichweite(),
                    value.getZielkategorie(),
                    value.getWirkungsdauer(),
                    value.getWirkung(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }

    public Zeremonie parseZeremonie(String url) {
        try {
            ParsedValue value = parseDetails(url);

            return new Zeremonie(
                    value.getNutzkosten(),
                    value.getProbe(),
                    value.getReichweite(),
                    value.getDauer(),
                    value.getWirkung(),
                    value.getWirkungsdauer(),
                    value.getKostentabelle(),
                    value.getZielkategorie(),
                    value.getName());
        } catch (IOException e) {
            return null;
        }
    }
}
