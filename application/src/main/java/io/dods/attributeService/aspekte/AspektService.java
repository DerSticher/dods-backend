package io.dods.attributeService.aspekte;

import io.dods.model.attribute.misc.Aspekt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class AspektService {

    @Autowired
    private AspektRepository aspektRepository;

    public Iterable<Aspekt> findAll() {
        return aspektRepository.findAll();
    }

    public Aspekt persist(Aspekt attribut) {
        return aspektRepository.save(attribut);
    }

    public Aspekt findById(long id) {
        return aspektRepository.findById(id);
    }

    public Aspekt findByNameOrCreate(String name) {
        Aspekt aspekt = aspektRepository.findByName(name);

        if (aspekt == null) {
            aspekt = new Aspekt(name);
            aspekt = aspektRepository.save(aspekt);
        }

        return aspekt;
    }
}
