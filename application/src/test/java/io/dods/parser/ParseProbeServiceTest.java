package io.dods.parser;

import io.dods.model.attribute.misc.Probe;
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
public class ParseProbeServiceTest extends AbstractParseServiceTest {

    @Autowired
    private ParseProbeService service;

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
        Probe probe = service.parseProbe(document);

        assertNotNull("line: " + line, probe);
        assertNotNull("line: " + line, probe.getTeilprobe(1));
        assertTrue("Expected \"Mut\" but was actual " + probe.getTeilprobe(1).getName(),
                "Mut".equals(probe.getTeilprobe(1).getName()));
        assertNotNull(probe.getTeilprobe(2));
        assertTrue("Expected \"Intuition\" but was actual " + probe.getTeilprobe(2).getName(),
                "Intuition".equals(probe.getTeilprobe(2).getName()));
        assertNotNull(probe.getTeilprobe(3));
        assertTrue("Expected \"Körperkraft\" but was actual " + probe.getTeilprobe(3).getName(),
                "Körperkraft".equals(probe.getTeilprobe(3).getName()));
    }

    @Test
    public void testNotFound() {
        Document document = Jsoup.parse("<html><body>Lorem Ipsum</body></html>");
        assertNull(service.parseProbe(document));
    }

}
