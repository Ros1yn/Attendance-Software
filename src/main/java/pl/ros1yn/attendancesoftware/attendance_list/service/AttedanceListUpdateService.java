package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.attendance_list.utils.update.AttendanceUpdateHelper;
import pl.ros1yn.attendancesoftware.exception.AttendanceListNotFoundException;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AttedanceListUpdateService {

    private final AttendanceListRepository attendanceListRepository;
    private final AttendanceListMapper attendanceListMapper;
    private final AttendanceUpdateHelper updateHelper;

    public ResponseEntity<AttendanceListResponse> updateAttendance(Integer id, AttendanceListRequestDTO requestDTO) {

        AttendanceList attendanceList = attendanceListRepository.findById(id)
                .orElseThrow(AttendanceListNotFoundException::new);
        List<Attendance> attendances = attendanceList.getAttendanceList();
        attendanceList.setDate(requestDTO.getDate());
        updateHelper.setNewLesson(requestDTO, attendanceList);

        if (requestDTO.getAttendances() == null) {
            attendanceList.setAttendanceList(null);
        } else if (requestDTO.getAttendances().isEmpty()) {
            attendanceList.setAttendanceList(Collections.emptyList());
        } else {
            List<AttendanceDTOForList> newListOfAttendances = requestDTO.getAttendances();
            updateHelper.setNewListOfAttendances(requestDTO, attendances, newListOfAttendances);
            attendanceList.setAttendanceList(attendances);
        }

        attendanceListRepository.save(attendanceList);

        return ResponseEntity.ok(attendanceListMapper.mapToResponseDTO(attendanceList));
    }

    //todo dokończyć metody

    public ResponseEntity<AttendanceListResponse> updateAttendanceListPartially(Integer id, AttendanceListRequestDTO requestDTO) {
        return null;
    }
}
