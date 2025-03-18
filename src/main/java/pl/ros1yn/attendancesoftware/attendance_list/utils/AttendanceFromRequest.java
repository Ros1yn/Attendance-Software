package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
@AllArgsConstructor
public class AttendanceFromRequest {

    private final AttendanceListChecker attendanceListChecker;

    public Attendance createNewAttendance(AttendanceDTOForList dtoForList, AttendanceList savedList) {

        Student student = attendanceListChecker.checkerForStudent(dtoForList);

        return Attendance.builder()
                .student(student)
                .isAttendance(dtoForList.getIsAttendance())
                .attendanceList(savedList)
                .activity(0)
                .build();
    }
}
