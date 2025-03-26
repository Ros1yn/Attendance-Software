package pl.ros1yn.attendancesoftware.attendance_list.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListResponse;
import pl.ros1yn.attendancesoftware.attendance_list.mapper.AttendanceListMapper;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.utils.AttendanceListUpdateHelper;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AttedanceListUpdateService {

    private final AttendanceListMapper attendanceListMapper;
    private final AttendanceListUpdateHelper updateHelper;
    private final ClassFinder classFinder;

    @Transactional
    public ResponseEntity<AttendanceListResponse> updateAttendanceList(Integer listId, AttendanceListRequestDTO requestDTO) {

        AttendanceList attendanceList = classFinder.findAttendanceList(listId);
        List<Attendance> attendances = attendanceList.getAttendances();

        attendanceList.setDate(requestDTO.getDate());

        Lesson lesson = updateHelper.setNewLesson(requestDTO);
        attendanceList.setLesson(lesson);

        AttendanceList savedNewAttendanceList = updateHelper.getUpdatedAttendanceList(requestDTO, attendanceList, attendances);

        log.debug("Attendance list has been fully updated");

        return ResponseEntity.ok(attendanceListMapper.mapToResponseDTO(savedNewAttendanceList));
    }

    @Transactional
    public ResponseEntity<AttendanceListResponse> updateAttendanceListPartially(Integer listId, AttendanceListRequestDTO requestDTO) {

        AttendanceList attendanceList = classFinder.findAttendanceList(listId);

        Optional.ofNullable(requestDTO.getLessonId())
                .map(classFinder::findLesson)
                .ifPresent(attendanceList::setLesson);

        Optional.ofNullable(requestDTO.getDate())
                .ifPresent(attendanceList::setDate);

        Optional.ofNullable(requestDTO.getAttendances())
                .filter(newAttendanceList -> newAttendanceList.size() == attendanceList.getAttendances().size())
                .ifPresent(newAttendanceList -> newAttendanceList.forEach(existingAttendance -> updateAttendance(existingAttendance, attendanceList.getAttendances())));

        log.debug("Attendance list has been partially updated");

        return ResponseEntity.ok(attendanceListMapper.mapToResponseDTO(attendanceList));
    }

    private void updateAttendance(AttendanceDTOForList requestAttendance, List<Attendance> attendances) {

        Attendance existingAttendance = classFinder.findAttendanceByIdFromList(requestAttendance.getAttendanceId(), attendances);
        Student student = classFinder.findStudent(requestAttendance.getIndexNumber());
        existingAttendance.setStudent(student);
        existingAttendance.setIsAttendance(requestAttendance.getIsAttendance());
        existingAttendance.setActivity(requestAttendance.getActivity());
    }

}
