package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;

@Service
@AllArgsConstructor
public class AttendanceGetService {

    private final AttendanceRepository attendanceRepository;

    public ResponseEntity<Attendance> getAttendance(int listId) {
        return attendanceRepository.findById(listId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Iterable<Attendance>> getAllAttendances() {
        return ResponseEntity.ok(attendanceRepository.findAll());
    }
}
