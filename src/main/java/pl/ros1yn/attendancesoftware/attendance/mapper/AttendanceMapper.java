package pl.ros1yn.attendancesoftware.attendance.mapper;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceClassFinder;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.utils.BuildStudentDTO;

import java.util.Optional;


@Component
@AllArgsConstructor
public class AttendanceMapper {

    private final AttendanceClassFinder classFinder;

    public void updateAttendanceFromPatchDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

        if (updateDTO.getIndexNumber() != null){
            Student student = classFinder.findStudent(updateDTO);
            attendance.setStudent(student);
        }
        if (updateDTO.getIsAttendance() != null) {
            attendance.setIsAttendance(updateDTO.getIsAttendance());
        }
        if (updateDTO.getActivity() != null) {
            attendance.setActivity(updateDTO.getActivity());
        }
        if (updateDTO.getListId() != null){
            AttendanceList attendanceList = classFinder.findAttendanceList(updateDTO);
            attendance.setAttendanceList(attendanceList);
        }
    }

    public void updateAttendanceFromPutDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

        //todo dodać nullexception do metod dla classFinder
        AttendanceList attendanceList = classFinder.findAttendanceList(updateDTO);
        attendance.setAttendanceList(attendanceList);
        Student student = classFinder.findStudent(updateDTO);
        attendance.setStudent(student);
        Optional.ofNullable(updateDTO.getIsAttendance())
                .ifPresentOrElse(attendance::setIsAttendance, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "isAttendance must be filled");
                });
        Optional.ofNullable(updateDTO.getActivity())
                .ifPresentOrElse(attendance::setActivity, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "activity must be filled");

                });
    }

    public AttendanceResponse mapToAttendanceResponse(Attendance attendance) {
        return AttendanceResponse.builder()
                .id(attendance.getId())
                .studentDTO(BuildStudentDTO.build(attendance))
                .isAttendance(attendance.getIsAttendance())
                .activity(attendance.getActivity())
                .build();
    }
}
