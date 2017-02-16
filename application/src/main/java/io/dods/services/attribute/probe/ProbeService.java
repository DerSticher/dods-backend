package io.dods.services.attribute.probe;

import io.dods.services.attribute.eigenschaft.EigenschaftService;
import io.dods.model.attribute.misc.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class ProbeService {

    @Autowired
    private EigenschaftService eigenschaftService;

    public Probe create(HatProbeApi hatProbeApi) {
        Probe probe = new Probe();

        probe.setTeilprobe(1, eigenschaftService.findById(hatProbeApi.getTeilprobe1Id()));
        probe.setTeilprobe(2, eigenschaftService.findById(hatProbeApi.getTeilprobe2Id()));
        probe.setTeilprobe(3, eigenschaftService.findById(hatProbeApi.getTeilprobe3Id()));

        return probe;
    }
}
