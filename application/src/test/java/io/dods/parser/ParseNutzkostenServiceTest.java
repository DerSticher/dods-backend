package io.dods.parser;

import io.dods.model.attribute.misc.Nutzkosten;
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

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
@RunWith(Parameterized.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParseNutzkostenServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseNutzkostenService service;

    @Parameterized.Parameter(0)
    public String expected;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"4 AsP + 2 pro minute", ParserSources.getHtml("AsP-Kosten", "4 AsP + 2 pro minute")},
                {"8 KaP", ParserSources.getHtml("KaP-Kosten", "8 KaP")}
        });
    }

    @Test
    public void testParse() {
        for (String line : lines) {
            Document document = Jsoup.parse(line);
            Nutzkosten nutzkosten = service.parseNutzkosten(document);
            assertNotNull("line: " + line, nutzkosten);
            assertTrue("line: " + line, expected.equals(nutzkosten.getName()));
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseNutzkosten(document));
    }

}
