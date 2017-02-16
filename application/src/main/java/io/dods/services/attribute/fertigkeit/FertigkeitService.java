package io.dods.services.attribute.fertigkeit;

import io.dods.services.attribute.AbstractAttributRepository;
import io.dods.services.attribute.AbstractAttributService;
import io.dods.services.attribute.probe.ProbeService;
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
