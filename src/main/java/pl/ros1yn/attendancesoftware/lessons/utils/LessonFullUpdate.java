package pl.ros1yn.attendancesoftware.lessons.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.lessons.repository.LessonRepository;

@Component
@AllArgsConstructor
public class LessonFullUpdate {

    private final LessonRepository lessonRepository;

    public Lesson update(LessonSimpleDTO lessonSimpleDTO, Lesson existingLesson) {
        existingLesson.setTitle(lessonSimpleDTO.getTitle());
        existingLesson.setSemester(lessonSimpleDTO.getSemester());
        existingLesson.setYear(lessonSimpleDTO.getYear());

        return lessonRepository.save(existingLesson);
    }
}
