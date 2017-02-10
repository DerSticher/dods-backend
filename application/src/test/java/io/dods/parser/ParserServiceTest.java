package io.dods.parser;

import io.dods.model.attribute.*;
import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.Zielkategorie;
import io.dods.model.publikation.Publikation;
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
        Kampftechnik value = service.parseKampftechnik("http://www.ulisses-regelwiki.de/index.php/kt_schwerter.html", false);

        assertNotNull(value);
        assertTrue("Schwerter".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/kt_schwerter.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseLiturgieTest() throws Exception {
        Liturgie value = service.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html");

        assertNotNull(value);
        assertTrue("Lautlos".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseRitualTest() throws Exception {
        Ritual value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertNotNull(value);
        assertTrue("Magischer Raub".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseSonderfertigkeitTest() throws Exception {
        List<Sonderfertigkeit> sonderfertigkeiten = service.parseSonderfertigkeit("http://www.ulisses-regelwiki.de/index.php/SF_Deichbauer.html", Sonderfertigkeit.Gruppe.PROFAN);

        assertEquals(1, sonderfertigkeiten.size());

        Sonderfertigkeit value = sonderfertigkeiten.get(0);

        assertNotNull(value);
        assertTrue("Deichbauer".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/SF_Deichbauer.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseSegenTest() throws Exception {
        Segen value = service.parseSegen("http://www.ulisses-regelwiki.de/index.php/Seg_Eidsegen.html");

        assertNotNull(value);
        assertTrue("Eidsegen".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Seg_Eidsegen.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseVorteilTest() throws Exception {
        Segen value = service.parseSegen("http://www.ulisses-regelwiki.de/index.php/V_Richtungssinn.html");

        assertNotNull(value);
        assertTrue("Richtungssinn".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/V_Richtungssinn.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseZauberTest() throws Exception {
        Segen value = service.parseSegen("http://www.ulisses-regelwiki.de/index.php/ZS_Ablativum.html");

        assertNotNull(value);
        assertTrue("Ablativum".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/ZS_Ablativum.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseZaubertrickTest() throws Exception {
        Zaubertrick value = service.parseZaubertrick("http://www.ulisses-regelwiki.de/index.php/ZT_Abk%C3%BChlung.html");

        assertNotNull(value);
        assertTrue("Abkühlung".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/ZT_Abk%C3%BChlung.html".equals(value.getWikiUrl()));
    }

    @Test
    public void parseZeremonieTest() throws Exception {
        Segen value = service.parseSegen("http://www.ulisses-regelwiki.de/index.php/Zer_Objektweihe.html");

        assertNotNull(value);
        assertTrue("Objektweihe".equals(value.getName()));
        assertTrue("http://www.ulisses-regelwiki.de/index.php/Zer_Objektweihe.html".equals(value.getWikiUrl()));
    }

    // =============================

    @Test
    public void testKostentabelle() {
        Liturgie value = service.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html");

        assertNotNull(value);
        assertEquals(Kostentabelle.B, value.getKostentabelle());
    }

    @Test
    public void testDauer() {
        Ritual value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertNotNull(value);
        assertTrue("30 Minuten".equals(value.getDauer().getName()));
    }

    @Test
    public void testNutzkosten() {
        Ritual value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertNotNull(value);
        assertTrue("4 AsP (Kosten nicht modifizierbar)".equals(value.getNutzkosten().getName()));
    }

    @Test
    public void testReichweite() {
        Ritual value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertNotNull(value);
        assertTrue("Berührung".equals(value.getReichweite().getName()));
    }

    @Test
    public void testWirkungsdauer() {
        Liturgie value = service.parseLiturgie("http://www.ulisses-regelwiki.de/index.php/Lit_Lautlos.html");

        assertTrue("aufrechterhaltend".equals(value.getWirkungsdauer().getName()));
    }

    @Test
    public void testZielkategorie() {
        Ritual value = service.parseRitual("http://www.ulisses-regelwiki.de/index.php/Rit_MagischerRaub.html");

        assertNotNull(value);

        List<Zielkategorie> zielkategorien = value.getZielkategorien();
        assertEquals(1, zielkategorien.size());
        assertTrue("Kulturschaffende".equals(zielkategorien.get(0).getName()));
    }

    @Test
    public void testPublikation() {
        List<Sonderfertigkeit> sonderfertigkeiten = service.parseSonderfertigkeit("http://www.ulisses-regelwiki.de/index.php/SF_Deichbauer.html", Sonderfertigkeit.Gruppe.PROFAN);

        assertEquals(1, sonderfertigkeiten.size());

        Sonderfertigkeit value = sonderfertigkeiten.get(0);

        assertNotNull(value);

        List<Publikation> publikations = value.getPublikations();

        assertNotNull(publikations);
        assertEquals(2, publikations.size());

        Publikation p1 = publikations.get(0);
        assertTrue("Die Streitenden Königreiche".equals(p1.getWerk().getName()));
        assertTrue("156".equals(p1.getDetails()));

        Publikation p2 = publikations.get(1);
        assertTrue("Deicherbe".equals(p2.getWerk().getName()));
        assertTrue("7".equals(p2.getDetails()));
    }
}
