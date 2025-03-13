package pl.ros1yn.attendancesoftware.attendance_list.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceUpdateHelper;
import pl.ros1yn.attendancesoftware.exception.AttendanceListNotFoundException;
import pl.ros1yn.attendancesoftware.exception.AttendanceListRequestExceptionHandler;
import pl.ros1yn.attendancesoftware.exception.LessonNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class AttedanceListUpdateService {

    private final AttendanceListRepository attendanceListRepository;
    private final AttendanceListMapper attendanceListMapper;
    private final AttendanceUpdateHelper updateHelper;
    private final LessonRepository lessonRepository;

    public ResponseEntity<AttendanceListResponse> updateAttendanceList(Integer id, AttendanceListRequestDTO requestDTO) {

        AttendanceList attendanceList = attendanceListRepository.findById(id)
                .orElseThrow(AttendanceListNotFoundException::new);
        List<Attendance> attendances = attendanceList.getAttendances();
        attendanceList.setDate(requestDTO.getDate());
        Lesson lesson = updateHelper.setNewLesson(requestDTO);
        attendanceList.setLesson(lesson);
        AttendanceList savedNewAttendanceList = updateHelper.getUpdatedAttendanceList(requestDTO, attendanceList, attendances);

        return ResponseEntity.ok(attendanceListMapper.mapToResponseDTO(savedNewAttendanceList));
    }

    public ResponseEntity<AttendanceListResponse> updateAttendanceListPartially(Integer id, AttendanceListRequestDTO requestDTO) {

        AttendanceList attendanceList = attendanceListRepository.findById(id)
                .orElseThrow(AttendanceListNotFoundException::new);
        if (requestDTO.getLessonId() != null) {
            Lesson lesson = lessonRepository.findById(requestDTO.getLessonId())
                    .orElseThrow(LessonNotFoundException::new);
            attendanceList.setLesson(lesson);
        }
        if (requestDTO.getDate() != null) {
            attendanceList.setDate(requestDTO.getDate());
        }
        if (requestDTO.getAttendances() != null) {
            if (requestDTO.getAttendances().size() == attendanceList.getAttendances().size()) {
                List<Attendance> attendances = attendanceList.getAttendances();
                List<Attendance> updatedAttendanceList = new ArrayList<>();
                IntStream.range(0, requestDTO.getAttendances().size())
                        .mapToObj(i -> attendanceListMapper.mapFromRequestDTOToAttendance(requestDTO, i, attendances))
                        .forEach(updatedAttendanceList::add);
                attendanceList.setAttendances(updatedAttendanceList);
            } else
                throw new AttendanceListRequestExceptionHandler("The Size of the list must be the same as existing one");
        }

        AttendanceList savedList = attendanceListRepository.save(attendanceList);

        return ResponseEntity.ok(attendanceListMapper.mapToResponseDTO(savedList));
    }


}
