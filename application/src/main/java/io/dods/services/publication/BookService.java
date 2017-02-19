package io.dods.services.publication;

import io.dods.interfaces.services.NamedDodsDatabaseService;
import io.dods.model.publication.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class BookService implements NamedDodsDatabaseService<Long, Book, BookRepository> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookRepository getRepository() {
        return bookRepository;
    }

    @Override
    public Book findByNameOrCreate(String name) {
        Book book = getRepository().findFirstByName(name);
        if (book == null) {
            book = new Book(name);
            book = bookRepository.save(book);
        }
        return book;
    }
}
