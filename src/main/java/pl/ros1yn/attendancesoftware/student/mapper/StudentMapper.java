package pl.ros1yn.attendancesoftware.student.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
public class StudentMapper {

    public StudentDTO mapToDTO(Student student) {
        return StudentDTO.builder()
                .indexNumber(student.getIndexNumber())
                .name(student.getName())
                .surname(student.getSurname())
                .build();
    }

}
