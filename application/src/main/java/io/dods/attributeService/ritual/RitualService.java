package io.dods.attributeService.ritual;

import io.dods.api.model.CreateRitual;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Ritual;
import io.dods.model.attribute.misc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class RitualService extends AbstractAttributService<Ritual, CreateRitual> {

    @Autowired
    private RitualRepository ritualRepository;

    @Autowired
    private DauerService dauerService;

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    @Autowired
    private ProbeService probeService;

    @Autowired
    private ZielkategorieService zielkategorieService;

    @Override
    protected AbstractAttributRepository<Ritual> getRepository() {
        return ritualRepository;
    }

    @Override
    protected Ritual parse(CreateRitual create) {
        List<Zielkategorie> zielkategorien = zielkategorieService.findByIds(create.getZielkategorienIds());
        Dauer dauer = dauerService.findById(create.getRitualDauerId());
        Probe probe = probeService.create(create);

        String name = create.getName();
        String wirkung = create.getWirkung();
        Kostentabelle kostentabelle = Kostentabelle.findOrThrow(create.getSteigerungsfaktor());
        Wirkungsdauer wirkungsdauer = wirkungsdauerService.findById(create.getWirkungsdauerId());
        int aspKosten = create.getNutzkosten();
        int reichweite = create.getReichweiteInSchritt();

        return new Ritual(aspKosten, dauer, kostentabelle, reichweite, probe, wirkung, wirkungsdauer, zielkategorien, name);
    }

}
