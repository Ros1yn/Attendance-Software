package pl.ros1yn.attendancesoftware.lesson.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lesson.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LessonGetService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public ResponseEntity<List<LessonResponse>> getAllLessons() {

        Iterable<Lesson> allLessons = lessonRepository.findAll();
        List<LessonResponse> convertedLessons = new ArrayList<>();
        for (Lesson lesson : allLessons) {
            convertedLessons.add(lessonMapper.mapToDTO(lesson));
        }

        return ResponseEntity.ok(convertedLessons);
    }

    public ResponseEntity<LessonResponse> getSingleLesson(Integer lessonId) {

        return lessonRepository.findById(lessonId)
                .map(lessonMapper::mapToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
