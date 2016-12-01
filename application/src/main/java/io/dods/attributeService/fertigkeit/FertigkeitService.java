package io.dods.attributeService.fertigkeit;

import io.dods.attributeService.AbstractAttributRepository;
import io.dods.attributeService.AbstractAttributService;
import io.dods.attributeService.probe.ProbeService;
import io.dods.model.attribute.Fertigkeit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class FertigkeitService extends AbstractAttributService<Fertigkeit> {

    @Autowired
    private FertigkeitRepository fertigkeitRepository;

    @Autowired
    private ProbeService probeService;

    @Override
    protected AbstractAttributRepository<Fertigkeit> getRepository() {
        return fertigkeitRepository;
    }

}
