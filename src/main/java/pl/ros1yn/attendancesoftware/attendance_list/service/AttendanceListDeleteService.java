package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class AttendanceListDeleteService {

    private final AttendanceListRepository attendanceListRepository;
    private final ClassFinder classFinder;

    public ResponseEntity<AttendanceListResponse> deleteAttendanceList(Integer id) {

        classFinder.findAttendanceList(id);
        attendanceListRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
