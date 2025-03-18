package pl.ros1yn.attendancesoftware.coding.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.dto.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.dto.CodingRequestDTO;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.lessons.mapper.LessonMapper;
import pl.ros1yn.attendancesoftware.lessons.model.Lesson;
import pl.ros1yn.attendancesoftware.student.mapper.StudentMapper;
import pl.ros1yn.attendancesoftware.student.model.Student;

@Component
@AllArgsConstructor
public class CodingMapper {

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

    public Coding getNewCoding(CodingRequestDTO requestDTO, Student foundedStudent, Lesson foundedLesson) {

        return Coding.builder()
                .student(foundedStudent)
                .lesson(foundedLesson)
                .group(requestDTO.getGroup())
                .build();
    }
}
