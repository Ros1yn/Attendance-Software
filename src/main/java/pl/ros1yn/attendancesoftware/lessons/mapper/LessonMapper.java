package pl.ros1yn.attendancesoftware.lessons.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

@Component
public class LessonMapper {

    public LessonDTO mapToDTO(Lesson lesson) {
        return LessonDTO.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .semester(lesson.getSemester())
                .year(lesson.getYear())
                .build();
    }

}
