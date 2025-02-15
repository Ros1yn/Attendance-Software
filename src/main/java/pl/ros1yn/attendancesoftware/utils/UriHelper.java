package pl.ros1yn.attendancesoftware.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.ros1yn.attendancesoftware.model.Student;

import java.net.URI;

@Component
public class UriHelper {

    public URI getUri(Student student) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{index}")
                .buildAndExpand(student.getIndexNumber())
                .toUri();
    }

}
