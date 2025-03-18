package pl.ros1yn.attendancesoftware.lessons.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

@Component
public class LessonFullUpdate {

    public Lesson update(LessonSimpleDTO lessonSimpleDTO, Lesson existingLesson) {
        existingLesson.setTitle(lessonSimpleDTO.getTitle());
        existingLesson.setSemester(lessonSimpleDTO.getSemester());
        existingLesson.setYear(lessonSimpleDTO.getYear());

        return existingLesson;
    }
}
