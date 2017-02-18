package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Duration;
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
public class ParseDurationServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseDurationService service;

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
            Duration duration = service.parseDuration(document);
            assertNotNull("line: " + line, duration);
            assertTrue("line: " + line, expected.equals(duration.getName()));
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseDuration(document));
    }

}
