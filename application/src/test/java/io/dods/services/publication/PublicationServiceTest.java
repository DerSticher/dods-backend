package io.dods.services.publication;

import io.dods.model.publication.Book;
import io.dods.model.publication.Publication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class PublicationServiceTest {

    PublicationRepository mockedRepository = Mockito.mock(PublicationRepository.class);
    BookService mockedBookService = Mockito.mock(BookService.class);

    private PublicationService mockedService;

    @Autowired
    private PublicationService service;

    @Autowired
    private BookService bookService;

    @Before
    public void setUp() throws Exception {
        Mockito.when(mockedRepository.save(any(Publication.class))).thenAnswer(invocation -> {
            Publication p = invocation.getArgumentAt(0, Publication.class);
            p.setId(100L);
            return p;
        });

        Mockito.when(mockedBookService.findByNameOrCreate(anyString())).thenAnswer(invocation -> {
            String s = invocation.getArgumentAt(0, String.class);
            return new Book(s);
        });

        mockedService = new PublicationService(mockedRepository, mockedBookService);
    }

    @Test
    public void testFindByBookAndPageOrCreate() {
        Publication publication = mockedService.findByBookAndPageOrCreate("TestWerk", 1337);

        assertNotNull(publication);
        assertTrue(publication.getId() > 0);
        assertTrue("TestWerk".equals(publication.getBook().getName()));
        assertEquals(1337, publication.getPage());
    }

    @Test
    public void testFindByBook() {
        Book book = bookService.findFirstByName("Regelwerk");

        List<Publication> publications = service.findByBook(book);

        assertNotNull(publications);
        assertTrue(publications.size() > 0);
    }

}
