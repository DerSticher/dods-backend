package io.dods.api.publications;

import io.dods.model.publication.Book;
import io.dods.model.publication.Publication;
import io.dods.services.publication.BookService;
import io.dods.services.publication.PublicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Richard Gottschalk
 */
@RestController
public class PublicationApi {

    @Autowired
    private BookService bookService;

    @Autowired
    private PublicationService publicationService;

    @ApiOperation(value = "Search for Publications.", response = Publication.class, responseContainer = "List")
    @RequestMapping(path = "publikationen", method = RequestMethod.GET)
    public List<Publication> search(@RequestParam(value = "bookName", required = false) String bookName,
                                    @RequestParam(value = "bookId", required = false) Long bookId,
                                    @RequestParam(value = "page", required = false) Integer page) {
        if (Objects.equals(bookName, ValueConstants.DEFAULT_NONE)) bookName = null;
        if (bookId == null) bookId = 0L;
        if (page == null) page = 0;

        Book book = null;
        if (!StringUtils.isEmpty(bookName)) {
            book = bookService.findFirstByName(bookName);
        } else if (bookId > 0) {
            book = bookService.findById(bookId);
        }

        if (book == null) throw new IllegalArgumentException("please provide at least a bookId or bookId");

        return publicationService.find(book, page);
    }

    @ApiOperation(value = "a single Publikation", response = Publication.class)
    @RequestMapping(path = "publikation/{id}", method = RequestMethod.GET)
    public Publication getPublicationById(long id) {
        return publicationService.findById(id);
    }

}
