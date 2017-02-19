package io.dods.api.books;

import io.dods.model.publication.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * @author Richard Gottschalk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("integration")
public class BookApiTest {

    @Autowired
    private BookApi bookApi;

    @Test
    public void testGetAll() throws Exception {
        List<Book> books = bookApi.getAll();

        assertNotNull(books);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testGet() {
        Book book = bookApi.get(1);

        assertNotNull(book);
        assertEquals(new Long(1), book.getId());
        assertTrue("Regelwerk".equals(book.getName()));
        assertTrue(book.getRequired());
        assertFalse(StringUtils.isEmpty(book.getShopUrl()));
    }
}
