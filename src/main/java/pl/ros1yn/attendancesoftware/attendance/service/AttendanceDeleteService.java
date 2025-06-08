package pl.ros1yn.attendancesoftware.attendance.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceFinder;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceDeleteService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceFinder attendanceFinder;

    @Transactional
    public ResponseEntity<Void> deleteAttendance(Integer attendanceId) {

        Attendance attendance = attendanceFinder.find(attendanceId);
        attendanceRepository.deleteById(attendanceId);

        log.info("Deleted succesfully. Body: {}", attendance);
        return ResponseEntity.noContent().build();
    }
}
