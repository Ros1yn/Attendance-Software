package pl.ros1yn.attendancesoftware.student.utils;

import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.dto.StudentResponse;

public class BuildStudentDTO {

    private BuildStudentDTO() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static StudentResponse build(Attendance attendance) {
        return StudentResponse.builder()
                .indexNumber(attendance.getStudent().getIndexNumber())
                .name(attendance.getStudent().getName())
                .surname(attendance.getStudent().getSurname())
                .build();
    }
}
