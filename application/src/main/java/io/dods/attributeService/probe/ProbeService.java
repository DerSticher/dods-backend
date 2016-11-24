package io.dods.attributeService.probe;

import io.dods.api.model.CreateProbe;
import io.dods.attributeService.attribute.AttributeService;
import io.dods.model.attribute.Attribut;
import io.dods.model.attribute.Eigenschaft;
import io.dods.model.attribute.misc.Probe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Richard Gottschalk
 */
@Service
public class ProbeService {

    @Autowired
    private ProbeRepository probeRepository;

    @Autowired
    private AttributeService attributeService;

    public Iterable<Probe> findAll() {
        return probeRepository.findAll();
    }

    public Probe persist(List<Long> attribute) {
        List<Eigenschaft> eigenschaften = new ArrayList<>();

        for (long id : attribute) {
            eigenschaften.add((Eigenschaft) attributeService.findById(id));
        }

        return probeRepository.save(new Probe(eigenschaften));
    }

    public Probe findById(long id) {
        return probeRepository.findById(id);
    }

    public Probe create(CreateProbe createProbe) {
        Probe probe = new Probe();

        List<Integer> attributIds = createProbe.getAttributIds();
        for (int i = 0; i < attributIds.size(); i++) {
            Integer attributeId = attributIds.get(i);
            Attribut attribut = attributeService.findById(attributeId);
            if (attribut instanceof Eigenschaft) {
                probe.addEigenschaft((Eigenschaft) attribut);
            } else {
                throw new IllegalArgumentException(
                        String.format("attribute at index %d is not %s (it is an instance of %s)",
                                i,
                                Eigenschaft.class.getSimpleName(),
                                attribut.getClass().getSimpleName()));
            }
        }

        return probeRepository.save(probe);
    }
}
