package pl.ros1yn.attendancesoftware.lessons.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonDTO;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;
import pl.ros1yn.attendancesoftware.lessons.utils.LessonFullUpdate;
import pl.ros1yn.attendancesoftware.lessons.utils.LessonPartiallyUpdate;
import pl.ros1yn.attendancesoftware.lessons.utils.LessonToDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;

    private final LessonToDTO lessonToDTO;

    private final LessonPartiallyUpdate lessonPartiallyUpdate;

    private final LessonFullUpdate lessonFullUpdate;

    public ResponseEntity<List<LessonDTO>> getAllLessons() {
        Iterable<Lesson> allLessons = lessonRepository.findAll();

        List<LessonDTO> convertedLessons = new ArrayList<>();

        for (Lesson lesson : allLessons) {
            convertedLessons.add(lessonToDTO.convertToDTO(lesson));
        }

        return ResponseEntity.ok(convertedLessons);
    }

    public ResponseEntity<LessonDTO> getSingleLesson(Integer id) {

        return lessonRepository.findById(id)
                .map(lessonToDTO::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public ResponseEntity<Lesson> fullUpdate(Integer id, LessonSimpleDTO lessonSimpleDTO) {
        return lessonRepository.findById(id)
                .map(existingLesson -> lessonFullUpdate.update(lessonSimpleDTO, existingLesson))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Powiązane z innymi danymi przez klucz obcy -> do naprawy/////////////
    public ResponseEntity<LessonDTO> deleteLesson(Integer id) {

        Optional<Lesson> optionalLesson = lessonRepository.findById(id);

        if (optionalLesson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        lessonRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
    ////////////////////////////////////////////////////////////////////////

    public ResponseEntity<Lesson> updatePartially(Integer id, LessonSimpleDTO lessonSimpleDTO) {
        return lessonRepository.findById(id)
                .map(existingLesson -> lessonPartiallyUpdate.update(lessonSimpleDTO, existingLesson))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
