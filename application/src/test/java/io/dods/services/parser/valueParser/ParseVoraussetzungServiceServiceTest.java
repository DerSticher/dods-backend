package io.dods.services.parser.valueParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(Parameterized.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseVoraussetzungServiceServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseVoraussetzungService service;

    @Parameterized.Parameter(value = 0)
    public String line;

    @Parameterized.Parameters
    public static String[] parameters() {
        List<String> html = new ArrayList<>();
        html.addAll(ParserSources.getHtml("Voraussetzung", "MU 15"));
        html.addAll(ParserSources.getHtml("Voraussetzungen", "MU 15"));
        return html.toArray(new String[html.size()]);
    }

    @Test
    public void testParse() {
        Document document = Jsoup.parse(line);
        String voraussetzung = service.parseVoraussetzung(document);
        assertNotNull("line: " + line, voraussetzung);
        assertTrue("line: " + line, "MU 15".equals(voraussetzung));
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseVoraussetzung(document));
    }

}
