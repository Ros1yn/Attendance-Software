package pl.ros1yn.attendancesoftware.attendance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class AttendanceDeleteService {

    private final AttendanceRepository attendanceRepository;
    private final ClassFinder classFinder;

    public ResponseEntity<Attendance> deleteAttendance(Integer id) {

        classFinder.findAttendance(id);
        attendanceRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
