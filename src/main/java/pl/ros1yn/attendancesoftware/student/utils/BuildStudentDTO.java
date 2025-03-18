package pl.ros1yn.attendancesoftware.student.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.dto.StudentDTO;

@Component
public class BuildStudentDTO {

    private BuildStudentDTO() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static StudentDTO build(Attendance attendance) {
        return StudentDTO.builder()
                .indexNumber(attendance.getStudent().getIndexNumber())
                .name(attendance.getStudent().getName())
                .surname(attendance.getStudent().getSurname())
                .build();
    }
}
