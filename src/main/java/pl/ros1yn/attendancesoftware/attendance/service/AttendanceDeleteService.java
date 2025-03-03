package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendanceDeleteService {

    private final AttendanceRepository attendanceRepository;

    public ResponseEntity<Attendance> deleteAttendance(Integer id) {

        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        if (optionalAttendance.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        attendanceRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
