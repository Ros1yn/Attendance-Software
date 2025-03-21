package pl.ros1yn.attendancesoftware.lessons.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.attendance_list.repository.AttendanceListRepository;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.utils.ClassFinder;

@Service
@AllArgsConstructor
public class LessonDeleteService {

    private final LessonRepository lessonRepository;
    private final ClassFinder classFinder;
    private final AttendanceListRepository attendanceListRepository;

    @Transactional
    public ResponseEntity<LessonResponse> deleteLesson(Integer id) {

        Lesson lesson = classFinder.findLesson(id);
        attendanceListRepository.deleteByLesson(lesson);
        lessonRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
