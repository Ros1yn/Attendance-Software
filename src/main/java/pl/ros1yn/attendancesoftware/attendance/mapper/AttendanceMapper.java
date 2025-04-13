package pl.ros1yn.attendancesoftware.attendance.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.utils.BuildStudentDTO;

@Component
public class AttendanceMapper {

    public AttendanceResponse mapToAttendanceResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .studentResponse(BuildStudentDTO.build(attendance))
                .isAttendance(attendance.getIsAttendance())
                .activity(attendance.getActivity())
                .build();
    }
}