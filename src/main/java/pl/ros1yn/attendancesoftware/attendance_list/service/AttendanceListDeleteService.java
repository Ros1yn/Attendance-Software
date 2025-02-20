package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AttendanceListDeleteService {

    private final AttendanceListRepository attendanceListRepository;

    public ResponseEntity<AttendanceListDTO> deleteAttendanceList(Integer id) {

        Optional<AttendanceList> optionalAttendanceList = attendanceListRepository.findById(id);

        if (optionalAttendanceList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        attendanceListRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
