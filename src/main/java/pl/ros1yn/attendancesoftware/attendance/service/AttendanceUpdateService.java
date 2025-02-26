package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTO;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceToDTO;

@Service
@AllArgsConstructor
public class AttendanceUpdateService {

    private final AttendanceRepository attendanceRepository;

    private final AttendanceMapper attendanceMapper;

    private final AttendanceToDTO attendanceToDTO;

    public ResponseEntity<AttendanceDTO> updateAttendance(Integer id, AttendanceUpdateDTO updateDTO) {

        return null;
    }

    public ResponseEntity<AttendanceDTO> updatePartially(Integer id, AttendanceUpdateDTO updateDTO) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendance not found"));

        attendanceMapper.updateAttendanceFromUpdateDTO(updateDTO, attendance);

        Attendance savedAttendance = attendanceRepository.save(attendance);

        //MapStruct wyświetla activity jako null, ale wartość w bazie danych się zmienia
        return ResponseEntity.ok(attendanceToDTO.convert(savedAttendance));
    }
}
