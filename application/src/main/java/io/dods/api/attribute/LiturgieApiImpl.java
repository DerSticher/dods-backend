package io.dods.api.attribute;

import io.dods.api.model.CreateLiturgie;
import io.dods.attributeService.liturgie.LiturgieService;
import io.dods.model.attribute.Liturgie;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class LiturgieApiImpl
        extends AbstractAttributeApiImpl<Liturgie, CreateLiturgie, LiturgieService>
        implements LiturgieApi {}
