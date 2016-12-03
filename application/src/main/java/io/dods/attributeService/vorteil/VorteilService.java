package io.dods.attributeService.vorteil;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.model.attribute.Vorteil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class VorteilService extends AbstractAttributService<Vorteil> {

    @Autowired
    private VorteilRepository vorteilRepository;

    @Override
    protected AbstractAttributRepository<Vorteil> getRepository() {
        return vorteilRepository;
    }

}