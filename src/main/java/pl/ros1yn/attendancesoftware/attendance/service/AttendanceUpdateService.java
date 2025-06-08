package pl.ros1yn.attendancesoftware.attendance.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceFinder;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceUpdateHelper;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceUpdateService {

    private final AttendanceMapper attendanceMapper;
    private final AttendanceUpdateHelper updateHelper;
    private final AttendanceFinder attendanceFinder;

    @Transactional
    public ResponseEntity<AttendanceResponse> updateFullAttendance(Integer attendanceId, AttendanceUpdateDTO updateDTO) {

        Attendance newAttendance = attendanceFinder.find(attendanceId);
        updateHelper.updateAttendanceFromPutDTO(updateDTO, newAttendance);
        AttendanceResponse attendanceResponse = attendanceMapper.mapToAttendanceResponse(newAttendance);

        getUpdateLog(attendanceResponse);
        return ResponseEntity.ok(attendanceResponse);
    }

    @Transactional
    public ResponseEntity<AttendanceResponse> updateAttendancePartially(Integer attendanceId, AttendanceUpdateDTO updateDTO) {

        Attendance newAttendance = attendanceFinder.find(attendanceId);
        updateHelper.updateAttendanceFromPatchDTO(updateDTO, newAttendance);
        AttendanceResponse attendanceResponse = attendanceMapper.mapToAttendanceResponse(newAttendance);
        getUpdateLog(attendanceResponse);
        return ResponseEntity.ok(attendanceResponse);
    }

    private static void getUpdateLog(AttendanceResponse updatedAttendance) {
        log.info("Attendance has been updated. New body: {}", updatedAttendance);
    }
}
