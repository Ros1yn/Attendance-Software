package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceListNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceListGetService {

    private final AttendanceListRepository attendanceListRepository;

    private final AttendanceListMapper attendanceListMapper;

    public ResponseEntity<List<AttendanceListResponse>> getAllAttendanceLists() {

        Iterable<AttendanceList> all = attendanceListRepository.findAll();
        List<AttendanceListResponse> attendanceListResponses = new ArrayList<>();
        all.forEach(attendanceList -> attendanceListResponses.add(attendanceListMapper.mapToResponseDTO(attendanceList)));

        return ResponseEntity.ok(attendanceListResponses);
    }

    public ResponseEntity<AttendanceListResponse> getSingleAttendanceList(Integer listId) {

        return attendanceListRepository.findById(listId)
                .map(attendanceListMapper::mapToResponseDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    log.error("Attendance list was not found");
                    return new AttendanceListNotFoundException();
                });
    }
}
