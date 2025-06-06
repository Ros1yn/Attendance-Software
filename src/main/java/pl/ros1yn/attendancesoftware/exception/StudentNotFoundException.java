package pl.ros1yn.attendancesoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StudentNotFoundException extends ResponseStatusException {

    public StudentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Student not found");
    }

}
