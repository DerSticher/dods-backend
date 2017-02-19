package io.dods.api.books;

import io.dods.model.publication.Book;
import io.dods.services.publication.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@RestController
public class BookApi {

    private final BookService bookService;

    @Autowired
    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }

    @ApiOperation(value = "a List containing every Werk", response = Book.class, responseContainer = "List")
    @RequestMapping(path = "werke", method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @ApiOperation(value = "a single Werk", response = Book.class, responseContainer = "List")
    @RequestMapping(path = "werk/{id}", method = RequestMethod.GET)
    public Book get(long id) {
        return bookService.findById(id);
    }

}
