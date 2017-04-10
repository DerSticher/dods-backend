package io.dods.api.parser;

import io.dods.model.properties.*;
import io.dods.services.parser.ParseListService;
import io.dods.services.misc.LoggerService;
import org.jetbrains.annotations.Nullable;
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

    private final ParseListService parseListService;

    @Nullable
    private final LoggerService loggerService;

    @Autowired
    public ParserApi(ParseListService parseListService, @Nullable LoggerService loggerService) {
        this.parseListService = parseListService;
        this.loggerService = loggerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void parseAll() {
        long timer = System.currentTimeMillis();

        parseKampftechnik();
        parseLiturgie();
        parseRitual();
        parseSonderfertigkeit();
        parseSegen();
        parseZauber();
        parseZaubertrick();
        parseZeremonie();

        // keep parsing Vorteile after all other Attributes
        parseVorteil();

        // and keep the dependencies in the last spot
        parseDependencies();

        if (loggerService != null) loggerService.info(getClass(), String.format("Parsing done. Time: %d ms", System.currentTimeMillis() - timer));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "kampftechnik", method = RequestMethod.POST)
    public List<CombatTechnique> parseKampftechnik() {
        List<CombatTechnique> kampftechniken = new ArrayList<>();

        kampftechniken.addAll(parseListService.parseCombatTechnique("http://www.ulisses-regelwiki.de/index.php/KT_Fernkampf.html", true));
        kampftechniken.addAll(parseListService.parseCombatTechnique("http://www.ulisses-regelwiki.de/index.php/KT_Nahkampftechniken.html", false));

        return kampftechniken;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "liturgie", method = RequestMethod.POST)
    public List<LiturgicalChant> parseLiturgie() {
        return parseListService.parseLiturgicalChant("http://www.ulisses-regelwiki.de/index.php/lt_liturgien.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "ritual", method = RequestMethod.POST)
    public List<Ritual> parseRitual() {
        return parseListService.parseRitual("http://www.ulisses-regelwiki.de/index.php/za_rituale.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "sonderfertigkeit", method = RequestMethod.POST)
    public List<SpecialAbility> parseSonderfertigkeit() {
        List<SpecialAbility> specialAbility = new ArrayList<>();

        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_AHNENZEICHEN, "http://www.ulisses-regelwiki.de/index.php/Ahnenzeichen.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.KARMAL, "http://www.ulisses-regelwiki.de/index.php/SF_karmal.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/SF_Magie.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_ALLGEMEIN, "http://www.ulisses-regelwiki.de/index.php/sf_allgemeine_sonderfertigkeiten.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH, "http://www.ulisses-regelwiki.de/index.php/bannundschutz.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_BEFEHL, "http://www.ulisses-regelwiki.de/index.php/Befehls_SF.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_DOLCH, "http://www.ulisses-regelwiki.de/index.php/drituale.html"));

        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_KAMPF_ERWEITERT, "http://www.ulisses-regelwiki.de/index.php/SF_Erweitertekampfstilsonderfertigkeiten.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_ELFENLIED, "http://www.ulisses-regelwiki.de/index.php/ESF_Elfenlieder.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_ERWEITERT, "http://www.ulisses-regelwiki.de/index.php/ErweiterteZaubersonderfertigkeiten.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_HERRSCHAFTSRITUAL, "http://www.ulisses-regelwiki.de/index.php/herituel.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_FLUCH, "http://www.ulisses-regelwiki.de/index.php/HSF_Hexenflueche.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_KAMPF, "http://www.ulisses-regelwiki.de/index.php/sf_kampfsonderfertigkeiten.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_KAMPF_STIL, "http://www.ulisses-regelwiki.de/index.php/SF_Kampfstilsonderfertigkeiten.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_PRUEGEL, "http://www.ulisses-regelwiki.de/index.php/SF_Pruegelsonderfertigkeiten.html"));

        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_SCHICKSALSPUNKT, "http://www.ulisses-regelwiki.de/index.php/SF_Schick.html"));
        // ... Sprachen und Schriften ...
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_STAB, "http://www.ulisses-regelwiki.de/index.php/SSF_Stabzauber.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.PROFAN_TIER, "http://www.ulisses-regelwiki.de/index.php/SF_Tier.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_VERTRAUTENTIER, "http://www.ulisses-regelwiki.de/index.php/VSF_Vertrautentricks.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_ELFENLIED_VERZERRT, "http://www.ulisses-regelwiki.de/index.php/verelfenl.html"));
        specialAbility.addAll(parseListService.parseSpecialAbility(SpecialAbility.Group.MAGISCH_STIL, "http://www.ulisses-regelwiki.de/index.php/Zauberstilsonderfertigkeiten.html"));

        return specialAbility;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "segen", method = RequestMethod.POST)
    public List<Bless> parseSegen() {
        return parseListService.parseBless("http://www.ulisses-regelwiki.de/index.php/Lit_Segnungen.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "vorteil", method = RequestMethod.POST)
    public List<Advantage> parseVorteil() {
        List<Advantage> advantage = new ArrayList<>();
        advantage.addAll(parseListService.parseAdvantage("http://www.ulisses-regelwiki.de/index.php/vorteile.html"));
        advantage.addAll(parseListService.parseAdvantage("http://www.ulisses-regelwiki.de/index.php/nachteile.html"));
        return advantage;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "zauber", method = RequestMethod.POST)
    public List<Spell> parseZauber() {
        return parseListService.parseSpell("http://www.ulisses-regelwiki.de/index.php/za_zaubersprueche.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "zaubertrick", method = RequestMethod.POST)
    public List<Cantrip> parseZaubertrick() {
        return parseListService.parseCantrip("http://www.ulisses-regelwiki.de/index.php/Zauber_Zaubertricks.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "zeremonie", method = RequestMethod.POST)
    public List<Ceremony> parseZeremonie() {
        return parseListService.parseCeremony("http://www.ulisses-regelwiki.de/index.php/lt_zeremonien.html");
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "voraussetzung", method = RequestMethod.POST)
    public void parseDependencies() {
        parseListService.parseDependencies();
    }
}
