package io.dods.api.properties;

import io.dods.model.properties.Skill;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Richard Gottschalk
 */
interface SkillApi extends AbstractPropertyApi<Skill> {

    @Override
    @ApiOperation(value = "a single Skill or HTTP 404", response = Skill.class)
    @RequestMapping(path = "fertigkeit/{id}", method = RequestMethod.GET)
    Skill get(long id);

}
