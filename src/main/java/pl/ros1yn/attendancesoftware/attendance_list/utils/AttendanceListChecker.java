package pl.ros1yn.attendancesoftware.attendance_list.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.attendance.DTO.AttendanceDTOForList;
import pl.ros1yn.attendancesoftware.attendance_list.DTO.AttendanceListRequestDTO;
import pl.ros1yn.attendancesoftware.exception.LessonNotFoundException;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

@Component
@AllArgsConstructor
public class AttendanceListChecker {

    private final StudentRepository studentRepository;

    private final LessonRepository lessonRepository;


    public Lesson checkerForLesson(AttendanceListRequestDTO requestDTO) {
        return lessonRepository.findById(requestDTO.getLessonId())
                .orElseThrow(LessonNotFoundException::new);
    }

    public Student checkerForStudent(AttendanceDTOForList dtoForList) {
        return studentRepository.findById(dtoForList.getIndexNumber())
                .orElseThrow(StudentNotFoundException::new);
    }

}
