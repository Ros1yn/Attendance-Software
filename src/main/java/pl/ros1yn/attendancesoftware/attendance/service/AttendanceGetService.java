package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceGetService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public ResponseEntity<AttendanceResponse> getAttendance(int attendanceId) {

        return attendanceRepository.findById(attendanceId)
                .map(attendanceMapper::mapToAttendanceResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    log.error("Attendance was not found");
                    return new AttendanceNotFoundException();
                });
    }

    public ResponseEntity<List<Attendance>> getAllAttendances() {
        return ResponseEntity.ok(attendanceRepository.findAll());
    }
}
