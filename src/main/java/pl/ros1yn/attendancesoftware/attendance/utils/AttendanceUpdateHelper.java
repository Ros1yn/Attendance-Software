package pl.ros1yn.attendancesoftware.attendance.utils;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListFinder;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.utils.StudentFinder;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AttendanceUpdateHelper {

    private final StudentFinder studentFinder;
    private final AttendanceListFinder attendanceListFinder;

    public void updateAttendanceFromPatchDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

        Optional.ofNullable(updateDTO.getIndexNumber())
                .ifPresent(indexNumber -> {
                    Student student = studentFinder.find(updateDTO.getIndexNumber());
                    attendance.setStudent(student);
                });

        Optional.ofNullable(updateDTO.getIsAttendance())
                .ifPresent(attendance::setIsAttendance);

        Optional.ofNullable(updateDTO.getActivity())
                .ifPresent(attendance::setActivity);

        Optional.ofNullable(updateDTO.getListId())
                .ifPresent(listId -> {
                    AttendanceList attendanceList = attendanceListFinder.find(listId);
                    attendance.setAttendanceList(attendanceList);
                });
    }

    public void updateAttendanceFromPutDTO(AttendanceUpdateDTO updateDTO, Attendance attendance) {

        AttendanceList attendanceList = attendanceListFinder.find(updateDTO.getListId());
        attendance.setAttendanceList(attendanceList);
        Student student = studentFinder.find(updateDTO.getIndexNumber());
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
}
