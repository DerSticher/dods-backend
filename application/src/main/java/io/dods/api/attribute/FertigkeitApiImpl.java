package io.dods.api.attribute;

import io.dods.api.model.CreateFertigkeit;
import io.dods.attributeService.fertigkeit.FertigkeitService;
import io.dods.model.attribute.Fertigkeit;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class FertigkeitApiImpl
        extends AbstractAttributeApiImpl<Fertigkeit, CreateFertigkeit, FertigkeitService>
        implements FertigkeitApi {}
