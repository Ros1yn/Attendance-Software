package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;

import java.util.List;

@Component
@AllArgsConstructor
public class AttendencesListFromRequest {

    private final AttendanceFromRequest attendanceFromRequest;

    public List<Attendance> createAttendancesList(AttendanceListRequestDTO requestDTO, AttendanceList savedList) {
        return requestDTO.getAttendances().stream()
                .map(dtoForList -> attendanceFromRequest.createNewAttendance(dtoForList, savedList))
                .toList();
    }
}
