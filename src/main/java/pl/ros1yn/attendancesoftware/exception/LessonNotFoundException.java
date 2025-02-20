package pl.ros1yn.attendancesoftware.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@NoArgsConstructor
public class LessonNotFoundException extends Exception {

    public ResponseStatusException throwException() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
    }

}
