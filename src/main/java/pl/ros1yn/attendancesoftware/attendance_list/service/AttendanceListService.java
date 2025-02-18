package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.utils.AttendanceToDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListToDTO;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceListService {

    private final AttendanceListToDTO attendanceListToDTO;

    private final AttendanceListRepository attendanceListRepository;

    public ResponseEntity<List<AttendanceListDTO>> getAllAttendanceLists() {

        Iterable<AttendanceList> all = attendanceListRepository.findAll();

        List<AttendanceListDTO> attendanceListDTOList = new ArrayList<>();

        for (AttendanceList attendanceListDTO : all){
            attendanceListDTOList.add(attendanceListToDTO.convertToDTO(attendanceListDTO));
        }


        return ResponseEntity.ok(attendanceListDTOList);
    }
}
