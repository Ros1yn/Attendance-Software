package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance.utils.ClassFinderForAttendance;

@Service
@AllArgsConstructor
public class AttendanceUpdateService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final ClassFinderForAttendance finderForAttendance;

    public ResponseEntity<AttendanceResponse> updateAttendance(Integer id, AttendanceUpdateDTO updateDTO) {

        Attendance attendance = finderForAttendance.findAttendance(id);
        attendanceMapper.updateAttendanceFromPutDTO(updateDTO, attendance);
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.ok(attendanceMapper.mapToAttendanceResponse(savedAttendance));
    }

    public ResponseEntity<AttendanceResponse> updatePartially(Integer id, AttendanceUpdateDTO updateDTO) {

        Attendance attendance = finderForAttendance.findAttendance(id);
        attendanceMapper.updateAttendanceFromPatchDTO(updateDTO, attendance);
        Attendance savedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.ok(attendanceMapper.mapToAttendanceResponse(savedAttendance));
    }
}
