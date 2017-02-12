package io.dods.parser.valueParser;

import io.dods.model.publikation.Publikation;
import io.dods.model.publikation.Werk;
import org.jsoup.Jsoup;
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
public class ParsePublicationServiceTest {

    @Autowired
    ParsePublikationService service;

    @Test
    public void parseSinglePublicationTest() throws Exception {
        String content = "<p>Publikation: Regelwerk Seite 123</p>";

        List<Publikation> publikations = service.parsePublikations(Jsoup.parse(content));

        assertEquals(1, publikations.size());

        Publikation publikation = publikations.get(0);
        assertNotNull(publikation.getWerk());
        assertEquals(123, publikation.getPage());

        Werk werk = publikation.getWerk();
        assertTrue("Regelwerk".equals(werk.getName()));
    }

    @Test
    public void parseMultiplePublicationsTest() throws Exception {
        String content = "<p>Publikation: Regelwerk Seite 123, Super Fancy Regelwerk Seite 1337</p>";

        List<Publikation> publikations = service.parsePublikations(Jsoup.parse(content));

        assertEquals(2, publikations.size());

        Publikation publikation1 = publikations.get(0);
        assertNotNull(publikation1.getWerk());
        assertEquals(123, publikation1.getPage());

        Werk werk1 = publikation1.getWerk();
        assertTrue("Regelwerk".equals(werk1.getName()));

        // second publication
        Publikation publikation2 = publikations.get(1);
        assertNotNull(publikation2.getWerk());
        assertEquals(1337, publikation2.getPage());

        Werk werk2 = publikation2.getWerk();
        assertTrue("Super Fancy Regelwerk".equals(werk2.getName()));
    }

    @Test
    public void parseNoPublicationTest() throws Exception {
        String content = "";

        List<Publikation> publikations = service.parsePublikations(Jsoup.parse(content));

        assertEquals(0, publikations.size());
    }
}
