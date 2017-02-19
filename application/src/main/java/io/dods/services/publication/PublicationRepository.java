package io.dods.services.publication;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.publication.Publication;
import io.dods.model.publication.Book;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
interface PublicationRepository extends DodsRepository<Publication, Long> {

    Publication findFirstByBookAndPage(Book book, int page);
    List<Publication> findByBookAndPage(Book book, int page);

    List<Publication> findAllByBookOrderByPageAsc(Book book);
}
