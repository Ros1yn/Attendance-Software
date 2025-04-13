package pl.ros1yn.attendancesoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AttendanceListNotFoundException extends ResponseStatusException {
    public AttendanceListNotFoundException() {
        super(HttpStatus.NOT_FOUND, "AttendanceList not found");
    }
}
