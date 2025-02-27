package pl.ros1yn.attendancesoftware.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class LessonNotFoundException extends ResponseStatusException {

    public LessonNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Lesson not found");
    }

}
