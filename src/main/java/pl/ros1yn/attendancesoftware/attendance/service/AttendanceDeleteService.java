package pl.ros1yn.attendancesoftware.attendance.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceDeleteService {

    private final AttendanceRepository attendanceRepository;
    private final ClassFinder classFinder;

    @Transactional
    public ResponseEntity<Attendance> deleteAttendance(Integer id) {

        classFinder.findAttendance(id);
        attendanceRepository.deleteById(id);

        log.info("Deleted succesfully");
        return ResponseEntity.noContent().build();
    }
}
