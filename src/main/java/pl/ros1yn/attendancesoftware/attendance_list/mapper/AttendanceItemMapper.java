package pl.ros1yn.attendancesoftware.attendance_list.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendancePostDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@Component
public class AttendanceItemMapper {

    public AttendancePostDTO toPostDTO(Attendance a) {
        return new AttendancePostDTO(new StudentDTO(
                a.getStudent().getIndexNumber(),
                a.getStudent().getName(),
                a.getStudent().getSurname()),
                a.getIsAttendance());
    }

}
