package pl.ros1yn.attendancesoftware.attendance.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class AttendanceUpdateService {

    private final AttendanceMapper attendanceMapper;
    private final ClassFinder classFinder;

    @Transactional
    public ResponseEntity<AttendanceResponse> updateAttendance(Integer id, AttendanceUpdateDTO updateDTO) {

        Attendance attendance = classFinder.findAttendance(id);
        attendanceMapper.updateAttendanceFromPutDTO(updateDTO, attendance);
        return ResponseEntity.ok(attendanceMapper.mapToAttendanceResponse(attendance));
    }

    @Transactional
    public ResponseEntity<AttendanceResponse> updatePartially(Integer id, AttendanceUpdateDTO updateDTO) {

        Attendance attendance = classFinder.findAttendance(id);
        attendanceMapper.updateAttendanceFromPatchDTO(updateDTO, attendance);
        return ResponseEntity.ok(attendanceMapper.mapToAttendanceResponse(attendance));
    }
}
