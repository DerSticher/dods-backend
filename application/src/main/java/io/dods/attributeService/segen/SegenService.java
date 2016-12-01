package io.dods.attributeService.segen;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.aspekte.AspektService;
import io.dods.attributeService.reichweite.ReichweiteService;
import io.dods.attributeService.wirkungsdauer.WirkungsdauerService;
import io.dods.attributeService.zielkategorie.ZielkategorieService;
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
