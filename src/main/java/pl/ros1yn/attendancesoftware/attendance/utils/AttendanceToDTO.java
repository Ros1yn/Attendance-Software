package pl.ros1yn.attendancesoftware.attendance.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@Component
public class AttendanceToDTO {


    public AttendanceDTO convert(Attendance attendance) {

        StudentDTO studentDTO = new StudentDTO(
                attendance.getStudent().getIndexNumber(),
                attendance.getStudent().getName(),
                attendance.getStudent().getSurname()
        );

        return new AttendanceDTO(
                attendance.getId(),
                studentDTO,
                attendance.getIsAttendance()
        );
    }
}
