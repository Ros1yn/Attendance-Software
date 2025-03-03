package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceListGetService {

    private final AttendanceListRepository attendanceListRepository;

    private final AttendanceListMapper attendanceListMapper;

    public ResponseEntity<List<AttendanceListResponse>> getAllAttendanceLists() {

        Iterable<AttendanceList> all = attendanceListRepository.findAll();
        List<AttendanceListResponse> attendanceListResponses = new ArrayList<>();
        all.forEach(attendanceList -> attendanceListResponses.add(attendanceListMapper.mapToResponseDTO(attendanceList)));

        return ResponseEntity.ok(attendanceListResponses);
    }

    public ResponseEntity<AttendanceListResponse> getSingleAttendanceList(Integer id) {

        return attendanceListRepository.findById(id)
                .map(attendanceListMapper::mapToResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
