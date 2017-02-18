package io.dods.model.publication;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
public class PublicationTest {

    @Test
    public void testConstructor() {
        Book book = new Book("TestWerk");

        Publication publication = new Publication(book, 1337);

        assertTrue("TestWerk".equals(publication.getBook().getName()));
        assertEquals(1337, publication.getPage());
    }

    @Test
    public void testId() throws Exception {
        Publication publication = new Publication();

        assertNull(publication.getId());
        publication.setId(1L);
        assertTrue(1L == publication.getId());
    }

    @Test
    public void testWerk() throws Exception {
        Publication publication = new Publication();

        assertNull(publication.getBook());
        Book book = new Book();
        book.setName("TestWerk");

        publication.setBook(book);

        assertTrue(book == publication.getBook());
    }

    @Test
    public void testDetails() throws Exception {
        Publication publication = new Publication();

        assertNull(publication.getBook());
        publication.setPage(137);

        assertEquals(137, publication.getPage());
    }


}
