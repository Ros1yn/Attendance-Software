package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;

@Service
@AllArgsConstructor
public class AttendancePostService {

    private final AttendanceRepository attendanceRepository;

    public ResponseEntity<Attendance> addAttendance(Attendance attendance) {

        Attendance savedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }
}
