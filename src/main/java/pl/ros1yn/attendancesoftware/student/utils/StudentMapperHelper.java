package pl.ros1yn.attendancesoftware.student.utils;

import lombok.AllArgsConstructor;
import org.mapstruct.Named;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

@Component
@AllArgsConstructor
public class StudentMapperHelper {

    private final StudentRepository studentRepository;

    @Named("mapIndexToStudent")
    public Student mapIndexToStudent(Integer indexNumber) {

        if (indexNumber == null) {
            return null;
        }

        return studentRepository.findById(indexNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student was not found"));

    }
}
