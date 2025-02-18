package pl.ros1yn.attendancesoftware.lessons.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonDTO;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;

import java.util.List;

@Component
public class LessonToDTO {

    public LessonDTO convertToDTO(Lesson lesson) {

        List<Integer> codingIds = lesson.getCoding().stream()
                .map(Coding::getId)
                .toList();

        return new LessonDTO(lesson.getId(),
                lesson.getTitle(),
                lesson.getSemester(),
                lesson.getYear(),
                codingIds
        );
    }


}
