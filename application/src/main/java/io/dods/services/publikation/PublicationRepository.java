package io.dods.services.publikation;

import io.dods.interfaces.repositories.DodsRepository;
import io.dods.model.publication.Publication;
import io.dods.model.publication.Book;

/**
 * @author Richard Gottschalk
 */
interface PublicationRepository extends DodsRepository<Publication, Long> {

    Publication findByBookAndPage(Book book, int page);

}
