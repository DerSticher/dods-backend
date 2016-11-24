package io.dods.attributeService.eigenschaft;

import io.dods.api.model.CreateEigenschaft;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.model.attribute.Eigenschaft;
import io.dods.model.attribute.misc.Kostentabelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class EigenschaftService extends AbstractAttributService<Eigenschaft, CreateEigenschaft> {

    @Autowired
    private EigenschaftRepository eigenschaftRepository;

    @Override
    protected AbstractAttributRepository<Eigenschaft> getRepository() {
        return eigenschaftRepository;
    }

    @Override
    protected Eigenschaft parse(CreateEigenschaft create) {
        String name = create.getName();
        Kostentabelle kostentabelle = Kostentabelle.findOrThrow(create.getSteigerungsfaktor());

        return new Eigenschaft(kostentabelle, name);
    }

}
