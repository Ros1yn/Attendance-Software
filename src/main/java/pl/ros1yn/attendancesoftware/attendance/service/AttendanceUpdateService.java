package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;

@Service
@AllArgsConstructor
public class AttendanceUpdateService {


    public ResponseEntity<Attendance> updateAttendance(Integer id, Attendance attendance) {
        return null;
    }

    public ResponseEntity<Attendance> updatePartially(Integer id, Attendance attendance) {
        return null;
    }
}
