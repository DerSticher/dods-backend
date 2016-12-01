package io.dods.api.attribute;

import io.dods.attributeService.zeremonie.ZeremonieService;
import io.dods.model.attribute.Zeremonie;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Gottschalk
 */
@RestController
public class ZeremonieApiImpl
        extends AbstractAttributeApiImpl<Zeremonie, ZeremonieService>
        implements ZeremonieApi {}
