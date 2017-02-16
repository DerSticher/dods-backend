package io.dods.services.attribute.kampftechnik;

import io.dods.services.attribute.AbstractAttributRepository;
import io.dods.services.attribute.AbstractAttributService;
import io.dods.model.attribute.Kampftechnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class KampftechnikService extends AbstractAttributService<Kampftechnik> {

    @Autowired
    private KampftechnikRepository liturgieRepository;

    @Override
    protected AbstractAttributRepository<Kampftechnik> getRepository() {
        return liturgieRepository;
    }

}
