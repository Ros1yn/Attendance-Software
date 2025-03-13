package pl.ros1yn.attendancesoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AttendanceListRequestExceptionHandler extends ResponseStatusException {
    public AttendanceListRequestExceptionHandler(String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }

}
