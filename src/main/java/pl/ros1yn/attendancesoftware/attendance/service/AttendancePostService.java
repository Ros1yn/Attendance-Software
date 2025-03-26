package pl.ros1yn.attendancesoftware.attendance.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceResponse;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceUpdateDTO;
import pl.ros1yn.attendancesoftware.attendance.mapper.AttendanceMapper;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
@Slf4j
public class AttendancePostService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final ClassFinder classFinder;

    @Transactional
    public ResponseEntity<AttendanceResponse> addAttendance(AttendanceUpdateDTO updateDTO) {

        Student student = classFinder.findStudent(updateDTO.getIndexNumber());
        AttendanceList attendanceList = classFinder.findAttendanceList(updateDTO.getListId());

        Attendance newAttendance = Attendance.builder()
                .student(student)
                .isAttendance(updateDTO.getIsAttendance())
                .activity(updateDTO.getActivity())
                .attendanceList(attendanceList)
                .build();

        Attendance savedAttendance = attendanceRepository.save(newAttendance);

        log.info("Added succesfully. Saved body: {}", savedAttendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceMapper.mapToAttendanceResponse(savedAttendance));
    }
}
