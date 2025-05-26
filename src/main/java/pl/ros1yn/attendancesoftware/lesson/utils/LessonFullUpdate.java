package pl.ros1yn.attendancesoftware.lesson.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonRequest;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

@Component
@Slf4j
public class LessonFullUpdate {

    public Lesson update(LessonRequest lessonRequest, Lesson existingLesson) {
        existingLesson.setTitle(lessonRequest.getTitle());
        existingLesson.setSemester(lessonRequest.getSemester());
        existingLesson.setYear(lessonRequest.getYear());

        log.info("Lesson has been updated. Body: {}", existingLesson);
        return existingLesson;
    }
}
