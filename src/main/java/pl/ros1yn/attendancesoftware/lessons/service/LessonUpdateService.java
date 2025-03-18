package pl.ros1yn.attendancesoftware.lessons.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.lessons.utils.LessonFullUpdate;
import pl.ros1yn.attendancesoftware.lessons.utils.LessonPartiallyUpdate;

@Service
@AllArgsConstructor
public class LessonUpdateService {

    private final LessonRepository lessonRepository;
    private final LessonFullUpdate lessonFullUpdate;
    private final LessonPartiallyUpdate lessonPartiallyUpdate;

    @Transactional
    public ResponseEntity<Lesson> fullUpdate(Integer id, LessonSimpleDTO lessonSimpleDTO) {
        return lessonRepository.findById(id)
                .map(existingLesson -> lessonFullUpdate.update(lessonSimpleDTO, existingLesson))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<Lesson> updatePartially(Integer id, LessonSimpleDTO lessonSimpleDTO) {
        return lessonRepository.findById(id)
                .map(existingLesson -> lessonPartiallyUpdate.update(lessonSimpleDTO, existingLesson))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
