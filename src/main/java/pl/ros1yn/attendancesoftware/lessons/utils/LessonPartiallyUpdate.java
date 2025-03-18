package pl.ros1yn.attendancesoftware.lessons.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

import java.util.Optional;

@Component
@AllArgsConstructor
public class LessonPartiallyUpdate {
    public Lesson update(LessonSimpleDTO lessonSimpleDTO, Lesson existingLesson) {

        Optional.ofNullable(lessonSimpleDTO.getTitle())
                .ifPresent(existingLesson::setTitle);

        Optional.ofNullable(lessonSimpleDTO.getSemester())
                .ifPresent(existingLesson::setSemester);

        Optional.ofNullable(lessonSimpleDTO.getYear())
                .ifPresent(existingLesson::setYear);

        return existingLesson;
    }
}
