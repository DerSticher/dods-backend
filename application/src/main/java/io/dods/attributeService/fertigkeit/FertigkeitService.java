package io.dods.attributeService.fertigkeit;

import io.dods.api.model.CreateFertigkeit;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.model.attribute.Fertigkeit;
import io.dods.model.attribute.misc.Kostentabelle;
import io.dods.model.attribute.misc.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class FertigkeitService extends AbstractAttributService<Fertigkeit, CreateFertigkeit> {

    @Autowired
    private FertigkeitRepository fertigkeitRepository;

    @Autowired
    private ProbeService probeService;

    @Override
    protected AbstractAttributRepository<Fertigkeit> getRepository() {
        return fertigkeitRepository;
    }

    @Override
    protected Fertigkeit parse(CreateFertigkeit create) {
        Kostentabelle kostentabelle = Kostentabelle.findOrThrow(create.getSteigerungsfaktor());
        Probe probe = probeService.create(create);
        Fertigkeit.Gruppe gruppe = create.getGruppe();

        String name = create.getName();

        return new Fertigkeit(kostentabelle, probe, gruppe, name);
    }
}
