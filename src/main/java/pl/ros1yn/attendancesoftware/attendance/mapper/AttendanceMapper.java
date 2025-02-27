package pl.ros1yn.attendancesoftware.attendance.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;


//todo
@Component
public class AttendanceMapper {


    public void updateAttendanceFromPatchDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

    }

    public void updateAttendanceFromPutDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

    }

    public AttendanceResponse mapToAttendanceResponse(Attendance attendance) {

        StudentDTO studentDTO = new StudentDTO(
                attendance.getStudent().getIndexNumber(),
                attendance.getStudent().getName(),
                attendance.getStudent().getSurname());

        return AttendanceResponse.builder()
                .studentDTO(studentDTO)
                .isAttendance(attendance.getIsAttendance())
                .activity(attendance.getActivity())
                .build();
    }


}
