package pl.ros1yn.attendancesoftware.attendance.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;
import pl.ros1yn.attendancesoftware.utils.Finder;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class AttendanceFinder implements Finder<Attendance, Integer> {

    private final AttendanceRepository attendanceRepository;

    @Override
    public Attendance find(Integer attendanceId) {
        return attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> {
                    log.error("Attendance was not found");
                    return new AttendanceNotFoundException();
                });
    }

    public Attendance findAttendanceByIdFromList(Integer attendanceId, List<Attendance> attendanceList) {
        return attendanceList.stream()
                .filter(attendance -> attendance.getId().equals(attendanceId))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Attendance from list was not found");
                    return new AttendanceNotFoundException();
                });
    }
}
