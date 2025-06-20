package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.exception.AttendanceListRequestExceptionHandler;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.utils.LessonFinder;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.utils.StudentFinder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
@Slf4j
public class AttendanceListUpdateHelper {

    private StudentFinder studentFinder;
    private LessonFinder lessonFinder;

    public Lesson setNewLesson(AttendanceListRequestDTO requestDTO) {
        return Optional.ofNullable(requestDTO.getLessonId())
                .map(lessonFinder::find)
                .orElseThrow(() -> {
                    log.error("Lesson ID cannot be empty");
                    return new AttendanceListRequestExceptionHandler("LessonId cannot be empty");
                });
    }

    public AttendanceList getUpdatedAttendanceList(AttendanceListRequestDTO requestDTO, AttendanceList attendanceList, List<Attendance> attendances) {

        if (requestDTO.getAttendances() == null) {
            getEmptyAttendanceListLog();
            throw new AttendanceListRequestExceptionHandler(getAttendanceListRequestErrorReason());
        }

        List<AttendanceDTOForList> newListOfAttendances = requestDTO.getAttendances();
        List<Attendance> newList = newListOfAttendances.isEmpty()
                ? Collections.emptyList()
                : setNewListOfAttendances(requestDTO, attendances);

        attendanceList.setAttendances(newList);

        return attendanceList;
    }

    private List<Attendance> setNewListOfAttendances(AttendanceListRequestDTO requestDTO, List<Attendance> attendances) {

        List<AttendanceDTOForList> requestAttendances = Optional.ofNullable(requestDTO.getAttendances())
                .orElseThrow(() -> {
                    getEmptyAttendanceListLog();
                    return new AttendanceListRequestExceptionHandler(EmptyAttendanceListLog.EMPTY_ATTENDANCE_LIST_LOG.getMessageLog());
                });

        if (requestAttendances.size() != attendances.size()) {
            log.warn("The Size of the list must be the same as existing one");
            throw new AttendanceListRequestExceptionHandler("The Size of the list must be the same as existing one");
        }

        return IntStream.range(0, requestAttendances.size())
                .mapToObj(index -> {
                    AttendanceDTOForList attendanceDTO = requestAttendances.get(index);
                    Attendance existingAttendance = attendances.get(index);

                    Student student = studentFinder.find(attendanceDTO.getIndexNumber());

                    existingAttendance.setStudent(student);
                    existingAttendance.setIsAttendance(attendanceDTO.getIsAttendance());
                    existingAttendance.setActivity(0);

                    return existingAttendance;
                }).toList();
    }

    private static String getAttendanceListRequestErrorReason() {
        return EmptyAttendanceListLog.EMPTY_ATTENDANCE_LIST_LOG.getMessageLog();
    }

    private static void getEmptyAttendanceListLog() {
        log.warn(getAttendanceListRequestErrorReason());
    }
}
