package pl.ros1yn.attendancesoftware.student.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.student.dto.StudentResponse;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
public class StudentMapper {

    public StudentResponse mapToDTO(Student student) {
        return StudentResponse.builder()
                .indexNumber(student.getIndexNumber())
                .name(student.getName())
                .surname(student.getSurname())
                .build();
    }

}
