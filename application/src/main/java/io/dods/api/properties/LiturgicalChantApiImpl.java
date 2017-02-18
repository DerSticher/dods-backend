package io.dods.api.properties;

import io.dods.services.properties.liturgicalChant.LiturgicalChantService;
import io.dods.model.properties.LiturgicalChant;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class LiturgicalChantApiImpl
        extends AbstractPropertyApiImpl<LiturgicalChant, LiturgicalChantService>
        implements LiturgicalChantApi {}
