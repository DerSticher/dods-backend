package io.dods.attributeService.liturgie;

import io.dods.api.model.CreateLiturgie;
import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Liturgie;
import io.dods.model.attribute.misc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class LiturgieService extends AbstractAttributService<Liturgie, CreateLiturgie> {

    @Autowired
    private LiturgieRepository liturgieRepository;

    @Autowired
    private DauerService dauerService;

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    @Autowired
    private ProbeService probeService;

    @Autowired
    private ZielkategorieService zielkategorieService;

    @Autowired
    private ReichweiteService reichweiteService;

    @Override
    protected AbstractAttributRepository<Liturgie> getRepository() {
        return liturgieRepository;
    }

    @Override
    protected Liturgie parse(CreateLiturgie create) {
        List<Zielkategorie> zielkategorien = zielkategorieService.findByIds(create.getZielkategorienIds());
        Dauer dauer = dauerService.findById(create.getLiturgieDauerId());
        Probe probe = probeService.create(create);

        String name = create.getName();
        String wirkung = create.getWirkung();
        Kostentabelle kostentabelle = Kostentabelle.findOrThrow(create.getSteigerungsfaktor());
        Wirkungsdauer wirkungsdauer = wirkungsdauerService.findById(create.getWirkungsdauerId());
        int kap = create.getNutzkosten();
        Reichweite reichweite = reichweiteService.findById(create.getReichweiteId());

        return new Liturgie(dauer, kostentabelle, reichweite, kap, probe, wirkung, wirkungsdauer, zielkategorien, name);
    }

}
