package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
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

    public ResponseEntity<List<AttendanceListDTO>> getAllAttendanceLists() {

        Iterable<AttendanceList> all = attendanceListRepository.findAll();

        List<AttendanceListDTO> attendanceListDTOList = new ArrayList<>();

        attendanceListMapper.transfer(all, attendanceListDTOList);

        return ResponseEntity.ok(attendanceListDTOList);
    }

    public ResponseEntity<AttendanceListDTO> getSingleAttendanceList(Integer id) {
        return attendanceListRepository.findById(id)
                .map(attendanceListMapper::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
