package io.dods.services.parser.valueParser;

import io.dods.model.publication.Publication;
import io.dods.model.publication.Book;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ParsePublicationServiceTest {

    @Autowired
    ParsePublicationService service;

    @Test
    public void parseSinglePublicationTest() throws Exception {
        String content = "<p>Publikation: Regelwerk Seite 123</p>";

        List<Publication> publications = service.parsePublications(Jsoup.parse(content));

        assertEquals(1, publications.size());

        Publication publication = publications.get(0);
        assertNotNull(publication.getBook());
        assertEquals(123, publication.getPage());

        Book book = publication.getBook();
        assertTrue("Regelwerk".equals(book.getName()));
    }

    @Test
    public void parseMultiplePublicationsTest() throws Exception {
        String content = "<p>Publikation: Regelwerk Seite 123, Super Fancy Regelwerk Seite 1337</p>";

        List<Publication> publications = service.parsePublications(Jsoup.parse(content));

        assertEquals(2, publications.size());

        Publication publication1 = publications.get(0);
        assertNotNull(publication1.getBook());
        assertEquals(123, publication1.getPage());

        Book book1 = publication1.getBook();
        assertTrue("Regelwerk".equals(book1.getName()));

        // second publication
        Publication publication2 = publications.get(1);
        assertNotNull(publication2.getBook());
        assertEquals(1337, publication2.getPage());

        Book book2 = publication2.getBook();
        assertTrue("Super Fancy Regelwerk".equals(book2.getName()));
    }

    @Test
    public void parseNoPublicationTest() throws Exception {
        String content = "";

        List<Publication> publications = service.parsePublications(Jsoup.parse(content));

        assertEquals(0, publications.size());
    }
}
