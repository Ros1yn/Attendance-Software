package pl.ros1yn.attendancesoftware.lesson.mapper;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.lesson.dto.LessonResponse;
import pl.ros1yn.attendancesoftware.lesson.model.Lesson;

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
