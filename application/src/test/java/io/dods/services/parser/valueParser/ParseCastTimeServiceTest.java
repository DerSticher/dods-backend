package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.CastTime;
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
public class ParseCastTimeServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseCastTimeService service;

    @Parameterized.Parameter(value = 0)
    public String line;

    @Parameterized.Parameters
    public static String[] parameters() {
        List<String> html = new ArrayList<>();
        html.addAll(ParserSources.getHtml("Liturgiedauer", "2 Aktionen"));
        html.addAll(ParserSources.getHtml("Ritualdauer", "2 Aktionen"));
        html.addAll(ParserSources.getHtml("Zauberdauer", "2 Aktionen"));
        html.addAll(ParserSources.getHtml("Zeremoniedauer", "2 Aktionen"));
        return html.toArray(new String[html.size()]);
    }

    @Test
    public void testParse() {
        Document document = Jsoup.parse(line);
        CastTime castTime = service.parseCastTime(document);
        assertNotNull("line: " + line, castTime);
        assertTrue("line: " + line, "2 Aktionen".equals(castTime.getName()));
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseCastTime(document));
    }

}
