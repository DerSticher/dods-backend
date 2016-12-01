package io.dods.api.attribute;

import io.dods.attributeService.sonderfertigkeit.SonderfertigkeitService;
import io.dods.model.attribute.Sonderfertigkeit;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class SonderfertigkeitApiImpl
        extends AbstractAttributeApiImpl<Sonderfertigkeit, SonderfertigkeitService>
        implements SonderfertigkeitApi {}
