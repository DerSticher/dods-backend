package io.dods.attributeService.liturgie;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.dauer.DauerService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Liturgie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class LiturgieService extends AbstractAttributService<Liturgie> {

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

}
