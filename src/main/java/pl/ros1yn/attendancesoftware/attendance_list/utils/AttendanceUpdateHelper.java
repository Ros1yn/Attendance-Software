package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.exception.AttendanceListRequestExceptionHandler;
import pl.ros1yn.attendancesoftware.exception.LessonNotFoundException;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceUpdateHelper {

    private final LessonRepository lessonRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final AttendanceListRepository attendanceListRepository;

    public Lesson setNewLesson(AttendanceListRequestDTO requestDTO) {

        if (requestDTO.getLessonId() != null) {
            return lessonRepository.findById(requestDTO.getLessonId())
                    .orElseThrow(LessonNotFoundException::new);
        } else throw new AttendanceListRequestExceptionHandler("LessonId cannot be empty");
    }

    public AttendanceList getUpdatedAttendanceList(AttendanceListRequestDTO requestDTO, AttendanceList attendanceList, List<Attendance> attendances) {
        if (requestDTO.getAttendances() == null) {
            throw new AttendanceListRequestExceptionHandler("List of attendances cannot be empty");
        } else if (requestDTO.getAttendances().isEmpty()) {
            attendanceList.setAttendances(Collections.emptyList());
        } else {
            List<AttendanceDTOForList> newListOfAttendances = requestDTO.getAttendances();
            List<Attendance> newList = setNewListOfAttendances(requestDTO, attendances, newListOfAttendances);
            attendanceList.setAttendances(newList);
        }
        return attendanceListRepository.save(attendanceList);
    }

    private List<Attendance> setNewListOfAttendances(AttendanceListRequestDTO requestDTO, List<Attendance> attendances, List<AttendanceDTOForList> newListOfAttendances) {

        if (requestDTO.getAttendances() != null) {
            if (requestDTO.getAttendances().size() == attendances.size()) {
                return requestDTO.getAttendances().stream()
                        .map(attendance -> {
                            int index = requestDTO.getAttendances().indexOf(attendance);
                            Attendance existingAttendance = attendances.get(index);

                            Student student = studentRepository.findById(newListOfAttendances.get(index).getIndexNumber())
                                    .orElseThrow(StudentNotFoundException::new);

                            existingAttendance.setStudent(student);
                            existingAttendance.setIsAttendance(attendance.getIsAttendance());
                            existingAttendance.setActivity(0);

                            return attendanceRepository.save(existingAttendance);
                        }).toList();
            } else {
                throw new AttendanceListRequestExceptionHandler("The Size of the list must be the same as existing one");
            }
        } else {
            throw new AttendanceListRequestExceptionHandler("List of attendances cannot be empty");
        }
    }

}
