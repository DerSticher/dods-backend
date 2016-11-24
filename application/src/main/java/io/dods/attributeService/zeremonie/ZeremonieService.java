package io.dods.attributeService.zeremonie;

import io.dods.api.model.CreateZeremonie;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Zeremonie;
import io.dods.model.attribute.misc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Gottschalk
 */
@Service
public class ZeremonieService extends AbstractAttributService<Zeremonie, CreateZeremonie> {

    @Autowired
    private ZeremonieRepository zeremonieRepository;

    @Autowired
    private ProbeService probeService;

    @Autowired
    private DauerService dauerService;

    @Autowired
    private ZielkategorieService zielkategorieService;

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    @Override
    protected AbstractAttributRepository<Zeremonie> getRepository() {
        return zeremonieRepository;
    }

    @Override
    protected Zeremonie parse(CreateZeremonie create) {
        List<Zielkategorie> zielkategorieList = create.getZielkategorienId().stream()
                .map(id -> zielkategorieService.findById(id))
                .collect(Collectors.toList());

        Probe probe = probeService.create(create);
        int reichweite = create.getReichweiteInSchritt();
        String wirkung = create.getWirkung();
        Dauer dauer = dauerService.findById(create.getDauerId());
        Kostentabelle kostentabelle = Kostentabelle.findOrThrow(create.getSteigerungsfaktor());
        Wirkungsdauer wirkungsdauer = wirkungsdauerService.findById(create.getWirkungsdauerId());
        String name = create.getName();

        return new Zeremonie(probe, reichweite, dauer, wirkung, wirkungsdauer, kostentabelle, zielkategorieList, name);
    }
}
