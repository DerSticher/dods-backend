package io.dods.services.publikation;

import io.dods.interfaces.repositories.NamedDodsRepository;
import io.dods.model.publication.Book;

/**
 * @author Richard Gottschalk
 */
interface BookRepository extends NamedDodsRepository<Book, Long> {}
