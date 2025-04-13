package pl.ros1yn.attendancesoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CodingNotFoundException extends ResponseStatusException {
    public CodingNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Coding is not found");
    }
}
