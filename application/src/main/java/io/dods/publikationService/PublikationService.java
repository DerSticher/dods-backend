package io.dods.publikationService;

import io.dods.interfaces.services.DodsDatabaseService;
import io.dods.model.publikation.Publikation;
import io.dods.model.publikation.Werk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Richard Gottschalk
 */
@Service
public class PublikationService implements DodsDatabaseService<Long, Publikation, PublikationRepository> {

    private final PublikationRepository publikationRepository;

    private final WerkService werkService;

    @Autowired
    public PublikationService(PublikationRepository publikationRepository, WerkService werkService) {
        this.publikationRepository = publikationRepository;
        this.werkService = werkService;
    }

    @Override
    public PublikationRepository getRepository() {
        return publikationRepository;
    }

    public Publikation findByWerkAndPageOrCreate(String werkName, int page) {
        Werk werk = werkService.findByNameOrCreate(werkName);

        Publikation publikation = getRepository().findByWerkAndPage(werk, page);

        if (publikation == null) {
            publikation = new Publikation();
            publikation = getRepository().save(publikation);

            publikation.setWerk(werk);
            publikation.setPage(page);

            publikation = publikationRepository.save(publikation);
        }
        return publikation;
    }
}
