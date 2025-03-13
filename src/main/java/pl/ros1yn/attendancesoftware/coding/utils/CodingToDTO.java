package pl.ros1yn.attendancesoftware.coding.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.lessons.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.student.mapper.StudentMapper;

@Component
@AllArgsConstructor
public class CodingToDTO {

    private final StudentMapper studentMapper;
    private final LessonMapper lessonMapper;
    public CodingDTO mapToDTO(Coding coding) {

        return CodingDTO.builder()
                .id(coding.getId())
                .group(coding.getGroup())
                .studentDTO(
                        studentMapper.mapToDTO(coding.getStudent())
                )
                .lessonDTO(
                        lessonMapper.mapToDTO(coding.getLesson())
                ).build();
    }


}
