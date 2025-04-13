package pl.ros1yn.attendancesoftware.coding.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.exception.LessonNotFoundException;
import pl.ros1yn.attendancesoftware.exception.StudentNotFoundException;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

@Component
@AllArgsConstructor
public class CodingAttendanceChecker {

    private final StudentRepository studentRepository;

    private final LessonRepository lessonRepository;

    public Student checkerForStudent(CodingRequestDTO requestDTO) {
        return studentRepository.findById(requestDTO.getIndexNumber())
                .orElseThrow(StudentNotFoundException::new);
    }


    public Lesson checkerForLesson(CodingRequestDTO requestDTO) {
        return lessonRepository.findById(requestDTO.getLessonId())
                .orElseThrow(LessonNotFoundException::new);
    }
}
