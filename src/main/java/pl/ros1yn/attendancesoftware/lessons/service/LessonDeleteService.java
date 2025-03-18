package pl.ros1yn.attendancesoftware.lessons.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonDeleteService {

    private final LessonRepository lessonRepository;

    //TODO Powiązane z innymi danymi przez klucz obcy -> do naprawy
    public ResponseEntity<LessonDTO> deleteLesson(Integer id) {

        Optional<Lesson> optionalLesson = lessonRepository.findById(id);
        if (optionalLesson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        optionalLesson.ifPresent(lessonRepository::delete);

        return ResponseEntity.noContent().build();
    }
}
