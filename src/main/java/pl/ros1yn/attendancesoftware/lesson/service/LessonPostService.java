package pl.ros1yn.attendancesoftware.lesson.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lesson.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;
import pl.ros1yn.attendancesoftware.lesson.repository.LessonRepository;

@Service
@AllArgsConstructor
public class LessonPostService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public ResponseEntity<LessonResponse> addLesson(Lesson lesson) {

        Lesson savedLesson = lessonRepository.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonMapper.mapToDTO(savedLesson));
    }

}
