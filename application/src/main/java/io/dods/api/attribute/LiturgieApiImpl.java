package io.dods.api.attribute;

import io.dods.services.attribute.liturgie.LiturgieService;
import io.dods.model.attribute.Liturgie;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class LiturgieApiImpl
        extends AbstractAttributeApiImpl<Liturgie, LiturgieService>
        implements LiturgieApi {}
