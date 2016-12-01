package io.dods.attributeService.eigenschaft;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.model.attribute.Eigenschaft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class EigenschaftService extends AbstractAttributService<Eigenschaft> {

    @Autowired
    private EigenschaftRepository eigenschaftRepository;

    @Override
    protected AbstractAttributRepository<Eigenschaft> getRepository() {
        return eigenschaftRepository;
    }

}
