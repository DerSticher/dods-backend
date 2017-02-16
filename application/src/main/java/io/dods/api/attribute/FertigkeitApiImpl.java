package io.dods.api.attribute;

import io.dods.services.attribute.fertigkeit.FertigkeitService;
import io.dods.model.attribute.Fertigkeit;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class FertigkeitApiImpl
        extends AbstractAttributeApiImpl<Fertigkeit, FertigkeitService>
        implements FertigkeitApi {}
