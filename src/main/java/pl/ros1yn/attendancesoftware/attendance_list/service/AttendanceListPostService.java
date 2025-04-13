package pl.ros1yn.attendancesoftware.attendance_list.service;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListPostDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListDTOMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListChecker;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendencesListFromRequest;
import pl.ros1yn.attendancesoftware.attendance_list.utils.SaveAttendanceList;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceListPostService {

    private final AttendanceRepository attendanceRepository;

    private final AttendanceListChecker attendanceListChecker;

    private final SaveAttendanceList saveAttendanceList;

    private final AttendencesListFromRequest attendencesListFromRequest;

    private final AttendanceListDTOMapper attendanceListDTOMapper;

    public ResponseEntity<AttendanceListPostDTO> addNewAttendanceList(AttendanceListRequestDTO requestDTO) {

        Lesson lesson = attendanceListChecker.checkerForLesson(requestDTO);

        AttendanceList savedList = saveAttendanceList.saveList(requestDTO, lesson);

        List<Attendance> attendances = attendencesListFromRequest.createAttendancesList(requestDTO, savedList);

        attendanceRepository.saveAll(attendances);

        AttendanceListPostDTO attendanceListDTO = attendanceListDTOMapper.mapToListPostDTO(savedList, attendances);

        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceListDTO);
    }


}
