package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.dto.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance_list.dto.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.exception.AttendanceListRequestExceptionHandler;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class AttendanceListUpdateHelper {

    private final ClassFinder classFinder;

    public Lesson setNewLesson(AttendanceListRequestDTO requestDTO) {
        return Optional.ofNullable(requestDTO.getLessonId())
                .map(classFinder::findLesson)
                .orElseThrow(() -> new AttendanceListRequestExceptionHandler("LessonId cannot be empty"));
    }

    public AttendanceList getUpdatedAttendanceList(AttendanceListRequestDTO requestDTO, AttendanceList attendanceList, List<Attendance> attendances) {

        if (requestDTO.getAttendances() == null) {
            throw new AttendanceListRequestExceptionHandler("List of attendances cannot be empty");
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
                .orElseThrow(() -> new AttendanceListRequestExceptionHandler("List of attendances cannot be empty"));

        if (requestAttendances.size() != attendances.size()) {
            throw new AttendanceListRequestExceptionHandler("The Size of the list must be the same as existing one");
        }

        return IntStream.range(0, requestAttendances.size())
                .mapToObj(index -> {
                    AttendanceDTOForList attendanceDTO = requestAttendances.get(index);
                    Attendance existingAttendance = attendances.get(index);

                    Student student = classFinder.findStudent(attendanceDTO.getIndexNumber());

                    existingAttendance.setStudent(student);
                    existingAttendance.setIsAttendance(attendanceDTO.getIsAttendance());
                    existingAttendance.setActivity(0);

                    return existingAttendance;
                }).toList();
    }

}
