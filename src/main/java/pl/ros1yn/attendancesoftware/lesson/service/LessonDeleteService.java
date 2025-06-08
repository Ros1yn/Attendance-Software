package pl.ros1yn.attendancesoftware.lesson.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.lesson.utils.LessonFinder;

@Service
@AllArgsConstructor
@Slf4j
public class LessonDeleteService {

    private final LessonRepository lessonRepository;
    private final LessonFinder lessonFinder;
    private final AttendanceListRepository attendanceListRepository;

    @Transactional
    public ResponseEntity<LessonResponse> deleteLesson(Integer lessonId) {

        Lesson lesson = lessonFinder.find(lessonId);
        attendanceListRepository.deleteByLesson(lesson);
        lessonRepository.deleteById(lessonId);

        log.info("Lesson has been deleted with its attendance lists. Body: {}", lesson);
        return ResponseEntity.noContent().build();
    }
}
