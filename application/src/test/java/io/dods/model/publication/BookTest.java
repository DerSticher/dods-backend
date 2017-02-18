package io.dods.model.publication;

import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * @author Richard Gottschalk
 */
public class BookTest {

    @Test
    public void testConstructor() {
        Book book = new Book("TestWerk");

        assertTrue("TestWerk".equals(book.getName()));
    }

    @Test
    public void testId() throws Exception {
        Book book = new Book();

        assertNull(book.getId());
        book.setId(1L);
        assertTrue(1L == book.getId());
    }

    @Test
    public void testDetails() throws Exception {
        Book book = new Book();

        assertNull(book.getName());
        book.setName("TestWerk");

        assertTrue("TestWerk".equals(book.getName()));
    }

}
