package io.dods.services.attribute.liturgie;

import io.dods.services.attribute.AbstractAttributRepository;
import io.dods.services.attribute.AbstractAttributService;
import io.dods.services.attribute.dauer.DauerService;
import io.dods.services.attribute.probe.ProbeService;
import io.dods.services.attribute.reichweite.ReichweiteService;
import io.dods.services.attribute.wirkungsdauer.WirkungsdauerService;
import io.dods.services.attribute.zielkategorie.ZielkategorieService;
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
