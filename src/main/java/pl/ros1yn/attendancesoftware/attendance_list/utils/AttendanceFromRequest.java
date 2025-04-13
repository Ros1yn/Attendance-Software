package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
@AllArgsConstructor
public class AttendanceFromRequest {

    private final AttendanceListChecker attendanceListChecker;

    public Attendance createNewAttendance(AttendanceDTOForList dtoForList, AttendanceList savedList) {
        Student student = attendanceListChecker.checkerForStudent(dtoForList);

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setIsAttendance(dtoForList.getIsAttendance());
        attendance.setAttendanceList(savedList);
        attendance.setActivity(0);

        return attendance;
    }
}
