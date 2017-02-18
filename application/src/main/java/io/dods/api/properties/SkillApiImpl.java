package io.dods.api.properties;

import io.dods.services.properties.skill.SkillService;
import io.dods.model.properties.Skill;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class SkillApiImpl
        extends AbstractPropertyApiImpl<Skill, SkillService>
        implements SkillApi {}
