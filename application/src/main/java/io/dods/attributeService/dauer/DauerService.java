package io.dods.attributeService.dauer;

import io.dods.model.attribute.misc.Dauer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class DauerService {

    @Autowired
    private DauerRepository dauerRepository;

    public Iterable<Dauer> findAll() {
        return dauerRepository.findAll();
    }

    public Dauer persist(Dauer dauer) {
        return dauerRepository.save(dauer);
    }

    public Dauer findById(long id) {
        return dauerRepository.findById(id);
    }

    public Dauer findByNameOrCreate(String name) {
        Dauer dauer = dauerRepository.findByName(name);
        if (dauer == null) {
            dauer = new Dauer(name);
            dauer = dauerRepository.save(dauer);
        }
        return dauer;
    }
}
