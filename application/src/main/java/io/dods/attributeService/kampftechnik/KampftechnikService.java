package io.dods.attributeService.kampftechnik;

import io.dods.api.model.CreateKampftechnik;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.eigenschaft.EigenschaftService;
import io.dods.model.attribute.Eigenschaft;
import io.dods.model.attribute.Kampftechnik;
import io.dods.model.attribute.misc.Kostentabelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class KampftechnikService extends AbstractAttributService<Kampftechnik, CreateKampftechnik> {

    @Autowired
    private KampftechnikRepository liturgieRepository;

    @Autowired
    private EigenschaftService eigenschaftService;

    @Override
    protected AbstractAttributRepository<Kampftechnik> getRepository() {
        return liturgieRepository;
    }

    @Override
    protected Kampftechnik parse(CreateKampftechnik create) {
        Eigenschaft leiteigenschaft = eigenschaftService.findById(create.getLeiteigenschaftId());
        Kostentabelle kostentabelle = Kostentabelle.findOrThrow(create.getSteigerungsfaktor());

        String name = create.getName();

        return new Kampftechnik(leiteigenschaft, kostentabelle, name);
    }
}
