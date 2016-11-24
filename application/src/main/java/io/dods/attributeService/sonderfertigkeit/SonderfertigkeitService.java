package io.dods.attributeService.sonderfertigkeit;

import io.dods.api.model.CreateSonderfertigkeit;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.model.attribute.Sonderfertigkeit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class SonderfertigkeitService extends AbstractAttributService<Sonderfertigkeit, CreateSonderfertigkeit> {

    @Autowired
    private SonderfertigkeitRepository sonderfertigkeitRepository;

    @Override
    protected AbstractAttributRepository<Sonderfertigkeit> getRepository() {
        return sonderfertigkeitRepository;
    }

    @Override
    protected Sonderfertigkeit parse(CreateSonderfertigkeit create) {
        int ap = create.getAp();
        boolean isPassive = create.isPassive();
        String name = create.getName();

        return new Sonderfertigkeit(ap, isPassive, name);
    }
}
