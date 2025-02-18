package pl.ros1yn.attendancesoftware.coding.utils;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.student.model.Student;
import pl.ros1yn.attendancesoftware.student.repository.StudentRepository;

@Component
@AllArgsConstructor
public class CodingAttendanceChecker {

    private final StudentRepository studentRepository;

    private final LessonRepository lessonRepository;

    public Student attendanceCheckerForStudent(CodingRequestDTO requestDTO) {
        return studentRepository.findById(requestDTO.getIndexNumber())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    public Lesson attendanceCheckerForLesson(CodingRequestDTO requestDTO) {
        return lessonRepository.findById(requestDTO.getLessonId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));
    }
}
