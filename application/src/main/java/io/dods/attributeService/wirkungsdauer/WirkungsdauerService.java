package io.dods.attributeService.wirkungsdauer;

import io.dods.model.attribute.misc.Wirkungsdauer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class WirkungsdauerService {

    @Autowired
    private WirkungsdauerRepository wirkungsdauerRepository;

    public Iterable<Wirkungsdauer> findAll() {
        return wirkungsdauerRepository.findAll();
    }

    public Wirkungsdauer persist(Wirkungsdauer wirkungsdauer) {
        return wirkungsdauerRepository.save(wirkungsdauer);
    }

    public Wirkungsdauer findById(long id) {
        return wirkungsdauerRepository.findById(id);
    }
}
