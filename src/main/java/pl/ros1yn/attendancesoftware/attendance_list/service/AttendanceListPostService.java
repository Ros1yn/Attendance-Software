package pl.ros1yn.attendancesoftware.attendance_list.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListChecker;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendencesListFromRequest;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceListPostService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceListChecker attendanceListChecker;
    private final AttendencesListFromRequest attendencesListFromRequest;
    private final AttendanceListRepository attendanceListRepository;
    private final AttendanceListMapper attendanceListMapper;

    public ResponseEntity<AttendanceListResponse> addNewAttendanceList(AttendanceListRequestDTO requestDTO) {

        Lesson lesson = attendanceListChecker.checkerForLesson(requestDTO);

        AttendanceList attendanceList = new AttendanceList();
        attendanceList.setDate(requestDTO.getDate());
        attendanceList.setLesson(lesson);
        AttendanceList savedList = attendanceListRepository.save(attendanceList);

        List<Attendance> attendances = attendencesListFromRequest.createAttendancesList(requestDTO, savedList);
        attendanceRepository.saveAll(attendances);
        attendanceList.setAttendances(attendances);

        AttendanceListResponse attendanceListResponse = attendanceListMapper.mapToResponseDTO(savedList);

        log.info("Attendance has been added. Body: {}", attendanceListResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceListResponse);
    }
}
