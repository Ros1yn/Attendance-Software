package pl.ros1yn.attendancesoftware.lessons.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lessons.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;

@Service
@AllArgsConstructor
public class LessonPostService {

    private final LessonRepository lessonRepository;
    private final LessonMapper mapper;

    public ResponseEntity<LessonResponse> addLesson(Lesson lesson) {

        Lesson savedLesson = lessonRepository.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.mapToDTO(savedLesson));
    }

}
