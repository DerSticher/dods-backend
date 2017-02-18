package io.dods.services.publikation;

import io.dods.interfaces.services.DodsDatabaseService;
import io.dods.model.publication.Book;
import io.dods.model.publication.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Publication publication = getRepository().findByBookAndPage(book, page);

        if (publication == null) {
            publication = new Publication();
            publication = getRepository().save(publication);

            publication.setBook(book);
            publication.setPage(page);

            publication = publicationRepository.save(publication);
        }
        return publication;
    }
}
