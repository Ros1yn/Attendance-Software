package pl.ros1yn.attendancesoftware.student.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.dto.StudentResponse;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuildStudentDTO {

    public static StudentResponse build(Attendance attendance) {
        return StudentResponse.builder()
                .indexNumber(attendance.getStudent().getIndexNumber())
                .name(attendance.getStudent().getName())
                .surname(attendance.getStudent().getSurname())
                .build();
    }
}
