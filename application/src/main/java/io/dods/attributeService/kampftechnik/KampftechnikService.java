package io.dods.attributeService.kampftechnik;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.eigenschaft.EigenschaftService;
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

    @Autowired
    private EigenschaftService eigenschaftService;

    @Override
    protected AbstractAttributRepository<Kampftechnik> getRepository() {
        return liturgieRepository;
    }

}
