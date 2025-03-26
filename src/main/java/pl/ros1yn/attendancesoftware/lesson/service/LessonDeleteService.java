package pl.ros1yn.attendancesoftware.lesson.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class LessonDeleteService {

    private final LessonRepository lessonRepository;
    private final ClassFinder classFinder;
    private final AttendanceListRepository attendanceListRepository;

    @Transactional
    public ResponseEntity<LessonResponse> deleteLesson(Integer lessonId) {

        Lesson lesson = classFinder.findLesson(lessonId);
        attendanceListRepository.deleteByLesson(lesson);
        lessonRepository.deleteById(lessonId);

        return ResponseEntity.noContent().build();
    }
}
