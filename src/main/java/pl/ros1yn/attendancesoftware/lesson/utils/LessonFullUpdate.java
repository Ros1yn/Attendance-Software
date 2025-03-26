package pl.ros1yn.attendancesoftware.lesson.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

@Component
public class LessonFullUpdate {

    public Lesson update(LessonSimpleDTO lessonSimpleDTO, Lesson existingLesson) {
        existingLesson.setTitle(lessonSimpleDTO.getTitle());
        existingLesson.setSemester(lessonSimpleDTO.getSemester());
        existingLesson.setYear(lessonSimpleDTO.getYear());

        return existingLesson;
    }
}
