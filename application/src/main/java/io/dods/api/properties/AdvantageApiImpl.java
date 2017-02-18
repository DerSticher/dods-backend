package io.dods.api.properties;

import io.dods.services.properties.advantage.AdvantageService;
import io.dods.model.properties.Advantage;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class AdvantageApiImpl
        extends AbstractPropertyApiImpl<Advantage, AdvantageService>
        implements AdvantageApi {}
