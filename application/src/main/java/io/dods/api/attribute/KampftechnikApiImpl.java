package io.dods.api.attribute;

import io.dods.api.model.CreateKampftechnik;
import io.dods.attributeService.kampftechnik.KampftechnikService;
import io.dods.model.attribute.Kampftechnik;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class KampftechnikApiImpl
        extends AbstractAttributeApiImpl<Kampftechnik, CreateKampftechnik, KampftechnikService>
        implements KampftechnikApi {}
