package io.dods.services.properties.skill;

import io.dods.model.properties.Skill;
import io.dods.services.properties.AbstractPropertyRepository;
import io.dods.services.properties.AbstractPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class SkillService extends AbstractPropertyService<Skill> {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    protected AbstractPropertyRepository<Skill> getRepository() {
        return skillRepository;
    }

}
