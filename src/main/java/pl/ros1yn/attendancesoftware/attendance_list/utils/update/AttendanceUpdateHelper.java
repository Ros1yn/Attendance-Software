package pl.ros1yn.attendancesoftware.attendance_list.utils.update;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance.model.Attendance;
import pl.ros1yn.attendancesoftware.attendance.repository.AttendanceRepository;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.attendance_list.model.AttendanceList;
import pl.ros1yn.attendancesoftware.exception.LessonNotFoundException;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class AttendanceUpdateHelper {

    private final LessonRepository lessonRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public void setNewLesson(AttendanceListRequestDTO requestDTO, AttendanceList attendanceList) {
        if (requestDTO.getLessonId() != null) {
            Lesson lesson = lessonRepository.findById(requestDTO.getLessonId())
                    .orElseThrow(LessonNotFoundException::new);
            attendanceList.setLesson(lesson);
        } else attendanceList.setLesson(null);
    }

    public void setNewListOfAttendances(AttendanceListRequestDTO requestDTO, List<Attendance> attendances, List<AttendanceDTOForList> newListOfAttendances) {

        if (requestDTO.getAttendances() != null && requestDTO.getAttendances().size() == attendances.size()) {
            for (int i = 0; i < attendances.size(); i++) {

                Attendance existingAttendance = attendances.get(i);
                AttendanceDTOForList newAttendance = newListOfAttendances.get(i);

                Student student = studentRepository.findById(newListOfAttendances.get(i).getIndexNumber())
                        .orElseThrow(StudentNotFoundException::new);

                existingAttendance.setStudent(student);
                existingAttendance.setIsAttendance(newAttendance.getIsAttendance());
                existingAttendance.setActivity(0);
                attendanceRepository.save(existingAttendance);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Size of the list must be the same as existing one");
        }
    }
}
