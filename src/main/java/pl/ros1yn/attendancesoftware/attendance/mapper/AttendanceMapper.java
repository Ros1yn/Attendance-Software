package pl.ros1yn.attendancesoftware.attendance.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.utils.ClassFinderForAttendance;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;
import pl.ros1yn.attendancesoftware.student.model.Student;


@Component
@AllArgsConstructor
public class AttendanceMapper {

    private ClassFinderForAttendance classFinder;

    public void updateAttendanceFromPatchDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

        if (updateDTO.getIndexNumber() != null){
            Student student = classFinder.findStudent(updateDTO);
            attendance.setStudent(student);
        }
        if (updateDTO.getIsAttendance() != null) attendance.setIsAttendance(updateDTO.getIsAttendance());
        if (updateDTO.getActivity() != null) attendance.setActivity(updateDTO.getActivity());
        if (updateDTO.getListId() != null){
            AttendanceList attendanceList = classFinder.findAttendanceList(updateDTO);
            attendance.setAttendanceList(attendanceList);
        }
    }

    public void updateAttendanceFromPutDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

        if (updateDTO.getIndexNumber() != null){
            Student student = classFinder.findStudentOrNull(updateDTO);
            attendance.setStudent(student);
        } else attendance.setStudent(null);
        attendance.setIsAttendance(updateDTO.getIsAttendance());
        attendance.setActivity(updateDTO.getActivity());
        if (updateDTO.getListId() != null){
            AttendanceList attendanceList = classFinder.findAttendanceList(updateDTO);
            attendance.setAttendanceList(attendanceList);
        } else attendance.setAttendanceList(null);
    }

    public AttendanceResponse mapToAttendanceResponse(Attendance attendance) {

        return AttendanceResponse.builder()
                .id(attendance.getId())
                .studentDTO(StudentDTO.builder()
                        .indexNumber(attendance.getStudent().getIndexNumber())
                        .name(attendance.getStudent().getName())
                        .surname(attendance.getStudent().getSurname())
                        .build())
                .isAttendance(attendance.getIsAttendance())
                .activity(attendance.getActivity())
                .build();
    }
}
