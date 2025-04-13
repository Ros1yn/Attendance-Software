package pl.ros1yn.attendancesoftware.lessons.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;

@Component
@AllArgsConstructor
public class LessonPartiallyUpdate {

    private final LessonRepository lessonRepository;

    public Lesson update(LessonSimpleDTO lessonSimpleDTO, Lesson existingLesson) {
        if (lessonSimpleDTO.getTitle() != null) existingLesson.setTitle(lessonSimpleDTO.getTitle());
        if (lessonSimpleDTO.getSemester() != null) existingLesson.setSemester(lessonSimpleDTO.getSemester());
        if (lessonSimpleDTO.getYear() != null) existingLesson.setYear(existingLesson.getYear());

        return lessonRepository.save(existingLesson);
    }
}
