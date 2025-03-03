package pl.ros1yn.attendancesoftware.coding.utils;

import org.springframework.stereotype.Component;
import pl.ros1yn.attendancesoftware.coding.DTO.CodingDTO;
import pl.ros1yn.attendancesoftware.coding.model.Coding;
import pl.ros1yn.attendancesoftware.lessons.DTO.LessonDTO;
import pl.ros1yn.attendancesoftware.student.DTO.StudentDTO;

@Component
public class CodingToDTO {

    public CodingDTO mapToDTO(Coding coding) {

        return CodingDTO.builder()
                .id(coding.getId())
                .group(coding.getGroup())
                .studentDTO(
                        StudentDTO.builder()
                                .indexNumber(coding.getStudent().getIndexNumber())
                                .name(coding.getStudent().getName())
                                .surname(coding.getStudent().getSurname())
                                .build())
                .lessonDTO(
                        LessonDTO.builder()
                                .id(coding.getLesson().getId())
                                .title(coding.getLesson().getTitle())
                                .semester(coding.getLesson().getSemester())
                                .year(coding.getLesson().getYear())
                                .build())
                .build();
    }
}
