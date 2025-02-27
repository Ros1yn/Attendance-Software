package pl.ros1yn.attendancesoftware.attendance.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;

@Component
@AllArgsConstructor
public class FindAttendanceById {

    private final AttendanceRepository attendanceRepository;

    public Attendance find(Integer id) {
        return attendanceRepository.findById(id)
                .orElseThrow(AttendanceNotFoundException::new);
    }

}
