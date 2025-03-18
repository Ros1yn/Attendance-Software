package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;

@Service
@RequiredArgsConstructor
public class AttendanceGetService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public ResponseEntity<AttendanceResponse> getAttendance(int listId) {

        return attendanceRepository.findById(listId)
                .map(attendanceMapper::mapToAttendanceResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(AttendanceNotFoundException::new);
    }

    public ResponseEntity<Iterable<Attendance>> getAllAttendances() {
        return ResponseEntity.ok(attendanceRepository.findAll());
    }
}
