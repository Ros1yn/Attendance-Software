package pl.ros1yn.attendancesoftware.lessons.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lessons.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

@Component
public class LessonMapper {

    public LessonResponse mapToDTO(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .semester(lesson.getSemester())
                .year(lesson.getYear())
                .build();
    }

}
