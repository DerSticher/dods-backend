package io.dods.services.parser;

import io.dods.model.attribute.*;
import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.Zielkategorie;
import io.dods.model.publikation.Publikation;
import io.dods.services.parser.model.ParsedData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParserServiceTest {

    @Autowired
    ParserService service;

    @Test
    public void parseKampftechnikTest() throws Exception {
        ParsedData<Kampftechnik> value = service.parseKampftechnik("http://www.ulisses-regelwiki.de/index.php/kt_schwerter.html", false);

        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Schwerter".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/kt_schwerter.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseLiturgieTest() throws Exception {
        ParsedData<Liturgie> value = service.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html");

        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Lautlos".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseRitualTest() throws Exception {
        ParsedData<Ritual> value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertTrue("Magischer Raub".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseSonderfertigkeitTest() throws Exception {
        ParsedData<Sonderfertigkeit> value = service.parseSonderfertigkeit("http://www.ulisses-regelwiki.de/index.php/SF_Deichbauer.html", Sonderfertigkeit.Gruppe.PROFAN);

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertTrue("Deichbauer".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/SF_Deichbauer.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseSegenTest() throws Exception {
        ParsedData<Segen> value = service.parseSegen("http://www.ulisses-regelwiki.de/index.php/Seg_Eidsegen.html");

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Eidsegen".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Seg_Eidsegen.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseVorteilTest() throws Exception {
        ParsedData<Vorteil> value = service.parseVorteil("http://www.ulisses-regelwiki.de/index.php/V_Richtungssinn.html");

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Richtungssinn".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/V_Richtungssinn.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseZauberTest() throws Exception {
        ParsedData<Zauber> value = service.parseZauber("http://www.ulisses-regelwiki.de/index.php/ZS_Ablativum.html");

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Ablativum".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/ZS_Ablativum.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseZaubertrickTest() throws Exception {
        ParsedData<Zaubertrick> value = service.parseZaubertrick("http://www.ulisses-regelwiki.de/index.php/ZT_Abk%C3%BChlung.html");

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Abkühlung".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/ZT_Abk%C3%BChlung.html".equals(value.getAttribut().getWikiUrl()));
    }

    @Test
    public void parseZeremonieTest() throws Exception {
        ParsedData<Zeremonie> value = service.parseZeremonie("http://www.ulisses-regelwiki.de/index.php/Zer_Objektweihe.html");

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Objektweihe".equals(value.getAttribut().getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Zer_Objektweihe.html".equals(value.getAttribut().getWikiUrl()));
    }

    // =============================

    @Test
    public void testKostentabelle() {
        ParsedData<Liturgie> value = service.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html");

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertEquals(Kostentabelle.B, value.getAttribut().getKostentabelle());
    }

    @Test
    public void testDauer() {
        ParsedData<Ritual> value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue(String.format("expected \"30 Minuten\" but actually was \"%s\"", value.getAttribut().getDauer().getName()),
                "30 Minuten".equals(value.getAttribut().getDauer().getName()));
    }

    @Test
    public void testNutzkosten() {
        ParsedData<Ritual> value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("4 AsP (Kosten nicht modifizierbar)".equals(value.getAttribut().getNutzkosten().getName()));
    }

    @Test
    public void testReichweite() {
        ParsedData<Ritual> value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);
        assertTrue("Berührung".equals(value.getAttribut().getReichweite().getName()));
    }

    @Test
    public void testWirkungsdauer() {
        ParsedData<Liturgie> value = service.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html");

        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertTrue("aufrechterhaltend".equals(value.getAttribut().getWirkungsdauer().getName()));
    }

    @Test
    public void testZielkategorie() {
        ParsedData<Ritual> value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());

        assertNotNull(value);

        List<Zielkategorie> zielkategorien = value.getAttribut().getZielkategorien();
        assertEquals(1, zielkategorien.size());
        assertTrue("Kulturschaffende".equals(zielkategorien.get(0).getName()));
    }

    @Test
    public void testPublikation() {
        ParsedData<Sonderfertigkeit> value = service.parseSonderfertigkeit("http://www.ulisses-regelwiki.de/index.php/SF_Deichbauer.html", Sonderfertigkeit.Gruppe.PROFAN);

        assertNotNull(value);
        assertEquals(0, value.getChildren().size());
        assertNotNull(value.getAttribut());


        List<Publikation> publikations = value.getAttribut().getPublikations();

        assertNotNull(publikations);
        assertEquals(2, publikations.size());

        Publikation p1 = publikations.get(0);
        assertTrue("Die Streitenden Königreiche".equals(p1.getWerk().getName()));
        assertEquals(156, p1.getPage());

        Publikation p2 = publikations.get(1);
        assertTrue("Deicherbe".equals(p2.getWerk().getName()));
        assertEquals(7, p2.getPage());
    }
}
