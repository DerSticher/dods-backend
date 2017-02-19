package io.dods.api.publications;

import io.dods.model.publication.Publication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.ValueConstants;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class PublicationApiTest {

    @Autowired
    private PublicationApi publicationApi;

    @Test
    public void testSearchByBookName() {
        List<Publication> publications = publicationApi.search("Regelwerk", null, null);

        assertNotNull(publications);
        assertTrue(publications.size() > 0);

        Publication firstPublication = publications.get(0);
        assertTrue("Regelwerk".equals(firstPublication.getBook().getName()));
    }

    @Test
    public void testSearchByBookId() {
        List<Publication> publications = publicationApi.search(ValueConstants.DEFAULT_NONE, 1L, null);

        assertNotNull(publications);
        assertTrue(publications.size() > 0);

        Publication firstPublication = publications.get(0);
        assertTrue("Regelwerk".equals(firstPublication.getBook().getName()));
    }

    @Test
    public void testSearchByBookNameAndPage() {
        List<Publication> allPublicationsInBook = publicationApi.search("Regelwerk", null, null);

        List<Publication> publications = publicationApi.search("Regelwerk", null, 163);

        assertNotNull(publications);
        assertTrue(publications.size() > 0);

        assertTrue(publications.size() < allPublicationsInBook.size());

        Publication firstPublication = publications.get(0);
        assertTrue("Regelwerk".equals(firstPublication.getBook().getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchWithoutBook() {
        publicationApi.search(null, null, 163);
    }

    @Test
    public void testGetAll() throws Exception {
        Publication publication = publicationApi.getPublicationById(2L);

        assertNotNull(publication);
        assertEquals(new Long(2), publication.getId());
        assertNotNull(publication.getBook());
        assertTrue(publication.getPage() > 0);
    }

}
