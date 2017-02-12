package io.dods.parser.valueParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParserNameServiceTest {

    @Autowired
    ParseNameService service;

    @Test
    public void parseNameTest() {
        Document document = Jsoup.parse("<html><body><h1>My fancy Name</h1></body></html>");
        String s = service.parseName(document);
        assertTrue("Expected name was \"My fancy Name\" but was " + s,
                "My fancy Name".equals(s));
    }

    @Test
    public void parseLeveledNameTest() {
        Document document = Jsoup.parse("<html><body><h1>My fancy Name I-X</h1></body></html>");
        String s = service.parseName(document);
        assertTrue(String.format("Expected name was \"My fancy Name I-X\" but was \"%s\"! " +
                        "This method should return the name with level range for further processing", s),
                "My fancy Name I-X".equals(s));
    }

    @Test
    public void parseTraditionNameTest() {
        Document document = Jsoup.parse("<html><body><h1>Die Tradition (Boronkirche) als Sonderfertigkeit</h1></body></html>");
        String s = service.parseName(document);
        assertTrue(String.format("Expected name was \"Tradition (Boronkirche)\" but was \"%s\"! " +
                        "This method should return the simplified name in case of stupid Adjectives", s),
                "Tradition (Boronkirche)".equals(s));
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        String s = service.parseName(document);
        assertTrue("This method should return an empty String", "".equals(s));
    }
}
