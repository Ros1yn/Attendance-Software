package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListFinder;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceListDeleteService {

    private final AttendanceListRepository attendanceListRepository;
    private final AttendanceListFinder attendanceListFinder;

    public ResponseEntity<AttendanceListResponse> removeAttendanceListById(Integer attendanceId) {

        AttendanceList attendanceList = attendanceListFinder.find(attendanceId);
        attendanceListRepository.deleteById(attendanceId);

        log.info("AttendanceList has been deleted. Body: {}", attendanceList);
        return ResponseEntity.noContent().build();
    }

}
