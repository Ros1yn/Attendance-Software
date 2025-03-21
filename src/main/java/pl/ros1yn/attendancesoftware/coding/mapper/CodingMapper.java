package pl.ros1yn.attendancesoftware.coding.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.dto.CodingResponse;
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

    public CodingResponse mapToDTO(Coding coding) {

        return CodingResponse.builder()
                .id(coding.getId())
                .group(coding.getGroup())
                .studentResponse(
                        studentMapper.mapToDTO(coding.getStudent())
                )
                .lessonResponse(
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
