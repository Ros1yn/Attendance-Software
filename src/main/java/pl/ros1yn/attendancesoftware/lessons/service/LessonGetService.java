package pl.ros1yn.attendancesoftware.lessons.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonDTO;
import pl.ros1yn.attendancesoftware.lessons.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonGetService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public ResponseEntity<List<LessonDTO>> getAllLessons() {

        Iterable<Lesson> allLessons = lessonRepository.findAll();
        List<LessonDTO> convertedLessons = new ArrayList<>();
        for (Lesson lesson : allLessons) {
            convertedLessons.add(lessonMapper.mapToDTO(lesson));
        }

        return ResponseEntity.ok(convertedLessons);
    }

    public ResponseEntity<LessonDTO> getSingleLesson(Integer id) {

        return lessonRepository.findById(id)
                .map(lessonMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
