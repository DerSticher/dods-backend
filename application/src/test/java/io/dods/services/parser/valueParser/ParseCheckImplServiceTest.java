package io.dods.services.parser.valueParser;

import io.dods.model.properties.misc.Check;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
public class ParseCheckImplServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseCheckService service;

    @Parameterized.Parameter(value = 0)
    public String line;

    @Parameterized.Parameters
    public static String[] parameters() {
        List<String> html = ParserSources.getHtml("Probe", "MU/IN/KK");
        return html.toArray(new String[html.size()]);
    }

    @Test
    public void testParse() {
        Document document = Jsoup.parse(line);
        Check check = service.parseProbe(document);

        assertNotNull("line: " + line, check);
        assertNotNull("line: " + line, check.getCheck(1));
        assertTrue("Expected \"Mut\" but was actual " + check.getCheck(1).getName(),
                "Mut".equals(check.getCheck(1).getName()));
        assertNotNull(check.getCheck(2));
        assertTrue("Expected \"Intuition\" but was actual " + check.getCheck(2).getName(),
                "Intuition".equals(check.getCheck(2).getName()));
        assertNotNull(check.getCheck(3));
        assertTrue("Expected \"Körperkraft\" but was actual " + check.getCheck(3).getName(),
                "Körperkraft".equals(check.getCheck(3).getName()));
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseProbe(document));
    }

}
