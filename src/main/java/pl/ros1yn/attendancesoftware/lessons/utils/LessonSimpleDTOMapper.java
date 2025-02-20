package pl.ros1yn.attendancesoftware.lessons.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonSimpleDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

@Component
public class LessonSimpleDTOMapper {

    public LessonSimpleDTO convertToSimpleDTO(Lesson lesson) {

        return new LessonSimpleDTO(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getSemester(),
                lesson.getYear()
        );

    }

}
