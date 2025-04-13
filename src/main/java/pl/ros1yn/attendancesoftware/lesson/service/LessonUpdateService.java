package pl.ros1yn.attendancesoftware.lesson.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.lesson.utils.LessonFullUpdate;
import pl.ros1yn.attendancesoftware.lesson.utils.PartialLessonUpdate;

@Service
@AllArgsConstructor
public class LessonUpdateService {

    private final LessonRepository lessonRepository;
    private final LessonFullUpdate lessonFullUpdate;
    private final PartialLessonUpdate partialLessonUpdate;

    @Transactional
    public ResponseEntity<Lesson> fullUpdate(Integer lessonId, LessonSimpleDTO lessonSimpleDTO) {
        return lessonRepository.findById(lessonId)
                .map(existingLesson -> lessonFullUpdate.update(lessonSimpleDTO, existingLesson))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<Lesson> updatePartially(Integer lessonId, LessonSimpleDTO lessonSimpleDTO) {
        return lessonRepository.findById(lessonId)
                .map(existingLesson -> partialLessonUpdate.update(lessonSimpleDTO, existingLesson))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
