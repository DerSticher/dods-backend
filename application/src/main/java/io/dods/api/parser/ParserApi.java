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

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "liturgie", method = RequestMethod.POST)
    public List<Liturgie> parse() {
        return parseListService.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/lt_liturgien.html");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "ritual", method = RequestMethod.POST)
    public List<Ritual> parseRitual() {
        return parseListService.parseRitual("http://www.ulisses-regelwiki.de/index.php/za_rituale.html");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "segen", method = RequestMethod.POST)
    public List<Segen> parseSegen() {
        return parseListService.parseSegen("http://www.ulisses-regelwiki.de/index.php/Lit_Segnungen.html");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "vorteil", method = RequestMethod.POST)
    public List<Vorteil> parseVorteil() {
        List<Vorteil> vorteil = new ArrayList<>();
        vorteil.addAll(parseListService.parseVorteil("http://www.ulisses-regelwiki.de/index.php/vorteile.html"));
        vorteil.addAll(parseListService.parseVorteil("http://www.ulisses-regelwiki.de/index.php/nachteile.html"));
        return vorteil;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "zauber", method = RequestMethod.POST)
    public List<Zauber> parseZauber() {
        return parseListService.parseZauber("http://www.ulisses-regelwiki.de/index.php/za_zaubersprueche.html");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "zaubertrick", method = RequestMethod.POST)
    public List<Zaubertrick> parseZaubertrick() {
        return parseListService.parseZaubertrick("http://www.ulisses-regelwiki.de/index.php/Zauber_Zaubertricks.html");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "zeremonie", method = RequestMethod.POST)
    public List<Zeremonie> parseZeremonie() {
        return parseListService.parseZeremonie("http://www.ulisses-regelwiki.de/index.php/lt_zeremonien.html");
    }
}
