package pl.ros1yn.attendancesoftware.lesson.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

import java.util.Optional;

@Component
@Slf4j
public class PartialLessonUpdate {

    public Lesson update(LessonSimpleDTO lessonSimpleDTO, Lesson existingLesson) {

        Optional.ofNullable(lessonSimpleDTO.getTitle())
                .ifPresent(existingLesson::setTitle);

        Optional.ofNullable(lessonSimpleDTO.getSemester())
                .ifPresent(existingLesson::setSemester);

        Optional.ofNullable(lessonSimpleDTO.getYear())
                .ifPresent(existingLesson::setYear);

        log.info("Lesson has been updated. Body: {}", existingLesson);
        return existingLesson;
    }
}
