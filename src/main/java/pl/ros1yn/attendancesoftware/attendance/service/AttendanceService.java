package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendancePartiallyUpdate;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceUpdate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final AttendanceUpdate attendanceUpdate;

    private final AttendancePartiallyUpdate attendancePartiallyUpdate;

    public ResponseEntity<Attendance> deleteAttendance(Integer id) {

        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);

        if (optionalAttendance.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        attendanceRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Attendance> addAttendance(Attendance attendance) {
        Attendance savedAttendance = attendanceRepository.save(attendance);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }

    public ResponseEntity<Optional<Attendance>> getAttendance(int listId) {

        Optional<Attendance> optionalAttendance = attendanceRepository.findById(listId);

        if (optionalAttendance.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalAttendance);
    }

    public ResponseEntity<Attendance> updateAttendance(Integer id, Attendance attendance) {

        return attendanceRepository.findById(id)
                .map(existingAttendance -> attendanceUpdate.update(attendance, existingAttendance))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Attendance> updatePartially(Integer id, Attendance attendance) {

        return attendanceRepository.findById(id)
                .map(existingAttendance -> attendancePartiallyUpdate.update(attendance, existingAttendance))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


}
