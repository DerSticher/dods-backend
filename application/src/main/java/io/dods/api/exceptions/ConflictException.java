package io.dods.api.exceptions;

import org.jetbrains.annotations.NonNls;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    public ConflictException(@NonNls String message) {
        super(message);
    }
}
