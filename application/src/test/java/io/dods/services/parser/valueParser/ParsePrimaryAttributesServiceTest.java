package io.dods.services.parser.valueParser;

import io.dods.model.properties.Ability;
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
public class ParsePrimaryAttributesServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParsePrimaryAttributesService service;

    @Parameterized.Parameter(0)
    public String expected;

    @Parameterized.Parameter(1)
    public List<String> lines;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"Mut", ParserSources.getHtml("Leiteigenschaft", "Mut")},
                {"Körperkraft", ParserSources.getHtml("Leiteigenschaft", "Körperkraft")},
                {"Fingerfertigkeit", ParserSources.getHtml("Leiteigenschaft", "Fingerfertigkeit")}
        });
    }

    @Test
    public void testParse() {
        for (String line : lines) {
            Document document = Jsoup.parse(line);
            Ability ability = service.parsePrimaryAttributes(document);
            assertNotNull("line: " + line, ability);
            assertTrue("line: " + line, expected.equals(ability.getName()));
        }
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parsePrimaryAttributes(document));
    }

}
