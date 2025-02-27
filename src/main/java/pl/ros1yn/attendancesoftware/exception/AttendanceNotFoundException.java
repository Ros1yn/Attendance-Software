package pl.ros1yn.attendancesoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AttendanceNotFoundException extends ResponseStatusException {

    public AttendanceNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Attedance not Found");
    }
}
