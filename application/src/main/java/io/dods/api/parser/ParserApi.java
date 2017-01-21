package io.dods.api.parser;

import io.dods.model.attribute.*;
import io.dods.parser.ParseListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@RestController
@RequestMapping("parse")
public class ParserApi {

    @Autowired
    private ParseListService parseListService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void parseAll() {
        parseKampftechnik();
        parseLiturgie();
        parseRitual();
        parseSonderfertigkeit();
        parseSegen();
        parseVorteil();
        parseZauber();
        parseZaubertrick();
        parseZeremonie();

        parseVoraussetzung();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "kampftechnik", method = RequestMethod.POST)
    public List<Kampftechnik> parseKampftechnik() {
        List<Kampftechnik> kampftechniken = new ArrayList<>();

        kampftechniken.addAll(parseListService.parseKampftechnik("http://www.ulisses-regelwiki.de/index.php/KT_Fernkampf.html", true));
        kampftechniken.addAll(parseListService.parseKampftechnik("http://www.ulisses-regelwiki.de/index.php/KT_Nahkampftechniken.html", false));

        return kampftechniken;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "liturgie", method = RequestMethod.POST)
    public List<Liturgie> parseLiturgie() {
        return parseListService.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/lt_liturgien.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "ritual", method = RequestMethod.POST)
    public List<Ritual> parseRitual() {
        return parseListService.parseRitual("http://www.ulisses-regelwiki.de/index.php/za_rituale.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "sonderfertigkeit", method = RequestMethod.POST)
    public List<Sonderfertigkeit> parseSonderfertigkeit() {
        List<Sonderfertigkeit> sonderfertigkeit = new ArrayList<>();

        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/Ahnenzeichen.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.KARMAL, "http://www.ulisses-regelwiki.de/index.php/SF_karmal.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/SF_Magie.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.PROFAN, "http://www.ulisses-regelwiki.de/index.php/sf_allgemeine_sonderfertigkeiten.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/bannundschutz.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.KAMPF, "http://www.ulisses-regelwiki.de/index.php/Befehls_SF.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/drituale.html"));

        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.KAMPF, "http://www.ulisses-regelwiki.de/index.php/SF_Erweitertekampfstilsonderfertigkeiten.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.PROFAN, "http://www.ulisses-regelwiki.de/index.php/ESF_Elfenlieder.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/ErweiterteZaubersonderfertigkeiten.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/herituel.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/HSF_Hexenflueche.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.KAMPF, "http://www.ulisses-regelwiki.de/index.php/sf_kampfsonderfertigkeiten.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.KAMPF, "http://www.ulisses-regelwiki.de/index.php/SF_Kampfstilsonderfertigkeiten.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.PROFAN, "http://www.ulisses-regelwiki.de/index.php/SF_Pruegelsonderfertigkeiten.html"));

        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.PROFAN, "http://www.ulisses-regelwiki.de/index.php/SF_Schick.html"));
        // ... Sprachen und Schriften ...
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/SSF_Stabzauber.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.PROFAN, "http://www.ulisses-regelwiki.de/index.php/SF_Tier.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.PROFAN, "http://www.ulisses-regelwiki.de/index.php/VSF_Vertrautentricks.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/verelfenl.html"));
        sonderfertigkeit.addAll(parseListService.parseSonderfertigkeit(Sonderfertigkeit.Gruppe.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/Zauberstilsonderfertigkeiten.html"));

        return sonderfertigkeit;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "segen", method = RequestMethod.POST)
    public List<Segen> parseSegen() {
        return parseListService.parseSegen("http://www.ulisses-regelwiki.de/index.php/Lit_Segnungen.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "vorteil", method = RequestMethod.POST)
    public List<Vorteil> parseVorteil() {
        List<Vorteil> vorteil = new ArrayList<>();
        vorteil.addAll(parseListService.parseVorteil("http://www.ulisses-regelwiki.de/index.php/vorteile.html"));
        vorteil.addAll(parseListService.parseVorteil("http://www.ulisses-regelwiki.de/index.php/nachteile.html"));
        return vorteil;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "zauber", method = RequestMethod.POST)
    public List<Zauber> parseZauber() {
        return parseListService.parseZauber("http://www.ulisses-regelwiki.de/index.php/za_zaubersprueche.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "zaubertrick", method = RequestMethod.POST)
    public List<Zaubertrick> parseZaubertrick() {
        return parseListService.parseZaubertrick("http://www.ulisses-regelwiki.de/index.php/Zauber_Zaubertricks.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "zeremonie", method = RequestMethod.POST)
    public List<Zeremonie> parseZeremonie() {
        return parseListService.parseZeremonie("http://www.ulisses-regelwiki.de/index.php/lt_zeremonien.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "voraussetzung", method = RequestMethod.POST)
    public void parseVoraussetzung() {
        parseListService.parseVorraussetzungen();
    }
}
