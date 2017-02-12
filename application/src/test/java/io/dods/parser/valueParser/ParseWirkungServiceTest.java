package io.dods.parser.valueParser;

import io.dods.model.attribute.misc.Wirkungsdauer;
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
public class ParseWirkungServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseWirkungService service;

    @Parameterized.Parameter(0)
    public String expected;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"30 Minuten", ParserSources.getHtml("Wirkungsdauer", "30 Minuten")},
                {"QS x 10 Minuten", ParserSources.getHtml("Wirkungsdauer", "QS x 10 Minuten")}
        });
    }

    @Test
    public void testParse() {
        for (String line : lines) {
            Document document = Jsoup.parse(line);
            Wirkungsdauer wirkungsdauer = service.parseWirkungsdauer(document);
            assertNotNull("line: " + line, wirkungsdauer);
            assertTrue("line: " + line, expected.equals(wirkungsdauer.getName()));
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseWirkungsdauer(document));
    }

}
