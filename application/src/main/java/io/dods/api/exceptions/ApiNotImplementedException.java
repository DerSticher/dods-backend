package io.dods.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Richard Gottschalk
 */
@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class ApiNotImplementedException extends RuntimeException {
}
