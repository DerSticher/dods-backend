package io.dods.services.parser.valueParser;

import io.dods.model.attribute.misc.Reichweite;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(Parameterized.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseReichweiteServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseReichweiteService service;

    @Parameterized.Parameter(0)
    public String expected;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"Berührung", ParserSources.getHtml("Reichweite", "Berührung")},
                {"QS x 3 in Meilen (nicht modifizierbar)", ParserSources.getHtml("Reichweite", "QS x 3 in Meilen (nicht modifizierbar)")},
                {"16 Schritt", ParserSources.getHtml("Reichweite", "16 Schritt")}
        });
    }

    @Test
    public void testParse() {
        for (String line : lines) {
            Document document = Jsoup.parse(line);
            Reichweite reichweite = service.parseReichweite(document);
            assertNotNull("line: " + line, reichweite);
            assertTrue("line: " + line, expected.equals(reichweite.getName()));
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseReichweite(document));
    }

}
