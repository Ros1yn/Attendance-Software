package pl.ros1yn.attendancesoftware.lesson.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

@Component
@Slf4j
public class LessonFullUpdate {

    public Lesson update(LessonSimpleDTO lessonSimpleDTO, Lesson existingLesson) {
        existingLesson.setTitle(lessonSimpleDTO.getTitle());
        existingLesson.setSemester(lessonSimpleDTO.getSemester());
        existingLesson.setYear(lessonSimpleDTO.getYear());

        log.info("Lesson has been updated. Body: {}", existingLesson);
        return existingLesson;
    }
}
