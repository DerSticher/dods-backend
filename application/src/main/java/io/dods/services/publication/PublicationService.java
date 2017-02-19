package io.dods.services.publication;

import io.dods.interfaces.services.DodsDatabaseService;
import io.dods.model.publication.Book;
import io.dods.model.publication.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class PublicationService implements DodsDatabaseService<Long, Publication, PublicationRepository> {

    private final PublicationRepository publicationRepository;

    private final BookService bookService;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository, BookService bookService) {
        this.publicationRepository = publicationRepository;
        this.bookService = bookService;
    }

    @Override
    public PublicationRepository getRepository() {
        return publicationRepository;
    }

    public Publication findByBookAndPageOrCreate(String werkName, int page) {
        Book book = bookService.findByNameOrCreate(werkName);

        Publication publication = getRepository().findFirstByBookAndPage(book, page);

        if (publication == null) {
            publication = new Publication();
            publication = getRepository().save(publication);

            publication.setBook(book);
            publication.setPage(page);

            publication = publicationRepository.save(publication);
        }
        return publication;
    }

    public List<Publication> findByBook(Book book) {
        return getRepository().findAllByBookOrderByPageAsc(book);
    }

    public List<Publication> find(Book book, int page) {
        if (book == null) throw new IllegalArgumentException("book can not be null");

        if (page > 0) {
            return getRepository().findByBookAndPage(book, page);
        } else {
            return getRepository().findAllByBookOrderByPageAsc(book);
        }
    }
}
