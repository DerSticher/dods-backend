package io.dods.services.attribute.segen;

import io.dods.services.attribute.AbstractAttributRepository;
import io.dods.services.attribute.AbstractAttributService;
import io.dods.services.attribute.aspekte.AspektService;
import io.dods.services.attribute.reichweite.ReichweiteService;
import io.dods.services.attribute.wirkungsdauer.WirkungsdauerService;
import io.dods.services.attribute.zielkategorie.ZielkategorieService;
import io.dods.model.attribute.Segen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class SegenService extends AbstractAttributService<Segen> {

    @Autowired
    private SegenRepository segenRepository;

    @Autowired
    private AspektService aspektService;

    @Autowired
    private WirkungsdauerService wirkungsdauerService;

    @Autowired
    private ZielkategorieService zielkategorieService;

    @Autowired
    private ReichweiteService reichweiteService;

    @Override
    protected AbstractAttributRepository<Segen> getRepository() {
        return segenRepository;
    }

}
